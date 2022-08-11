package br.com.microservices.productapi.modules.product.service;

import br.com.microservices.productapi.config.exception.ValidationException;
import br.com.microservices.productapi.modules.category.service.CategoryService;
import br.com.microservices.productapi.modules.product.dto.ProductCheckStockRequest;
import br.com.microservices.productapi.modules.product.dto.ProductQuantityDTO;
import br.com.microservices.productapi.modules.product.dto.ProductRequest;
import br.com.microservices.productapi.modules.product.dto.ProductSalesResponse;
import br.com.microservices.productapi.modules.product.dto.ProductStockDTO;
import br.com.microservices.productapi.modules.product.model.Product;
import br.com.microservices.productapi.modules.product.model.rabbitmq.SalesConfirmationSender;
import br.com.microservices.productapi.modules.product.repository.ProductRepository;
import br.com.microservices.productapi.modules.sales.client.SalesClient;
import br.com.microservices.productapi.modules.sales.dto.SalesConfirmationDTO;
import br.com.microservices.productapi.modules.sales.enums.SalesStatus;
import br.com.microservices.productapi.modules.supplier.service.SupplierService;
import br.com.microservices.productapi.utils.BigDecimalUtil;
import br.com.microservices.productapi.utils.LogUtil;
import br.com.microservices.productapi.utils.RequestUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final SupplierService supplierService;
    private final SalesClient salesClient;
    private final SalesConfirmationSender salesConfirmationSender;

    @Override
    public Product save(ProductRequest request) {
        LogUtil.logRequest(request);
        var category = categoryService.findById(request.getCategoryId());
        var supplier = supplierService.findById(request.getSupplierId());
        var response = productRepository.save(Product.of(request, category, supplier));
        LogUtil.logResponse(response);
        return productRepository.save(response);
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ValidationException("There's no product for the given ID."));
    }

    @Override
    public List<Product> findByCategoryId(Long categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }

    @Override
    public List<Product> findBySupplierId(Long supplierId) {
        return productRepository.findBySupplierId(supplierId);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> findByName(String name) {
        if (name.isBlank())
            throw new ValidationException("The Product name cannot be blank.");
        return productRepository.findByNameIgnoreCaseContaining(name);
    }

    @Override
    public Boolean existsByCategoryId(Long categoryId) {
        return productRepository.existsByCategoryId(categoryId);
    }

    @Override
    public Boolean existsBySupplierId(Long supplierId) {
        return productRepository.existsBySupplierId(supplierId);
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product update(ProductRequest request, Long id) {
        var category = categoryService.findById(request.getCategoryId());
        var supplier = supplierService.findById(request.getSupplierId());
        var product = Product.of(request, category, supplier);
        product.setId(id);
        return productRepository.save(product);
    }

    //TODO Refactor
    @Override
    public void updateProductStock(ProductStockDTO product) {
        try {
            validateStockUpdateData(product);
            updateStock(product);
        } catch (Exception ex) {
            log.error("Error while trying to update stock for message with error: {}", ex.getMessage(), ex);
            var rejectedSale = new SalesConfirmationDTO(product.getSalesId(), SalesStatus.REJECTED, product.getTransactionId());
            salesConfirmationSender.sendSalesConfirmationMessage(rejectedSale);
        }
    }

    @Override
    public ProductSalesResponse findProductSales(Long id) {
        var product = findById(id);
        try {
            var sales = salesClient.findSalesByProductId(id)
                    .orElseThrow(() -> new ValidationException("The sales was not found by this product"));
            return ProductSalesResponse.of(product, sales.getSalesIds());
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ValidationException("There was an error trying to get the product's sales.");
        }
    }

    @Override
    public void checkProductStock(ProductCheckStockRequest request) {
        LogUtil.logRequest(request);
        if (ObjectUtils.isEmpty(request) || ObjectUtils.isEmpty(request.getProducts()))
            throw new ValidationException("the request data must be informed.");

        request.getProducts().forEach(this::validateQuantityInStock);
    }

    @Transactional
    private void updateStock(ProductStockDTO productStock) {
        var productsForUpdate = new ArrayList<Product>();
        productStock.getProducts().forEach(salesProduct -> {
            var existingProduct = findById(salesProduct.getProductId());
            validateQuantityInStock(salesProduct, existingProduct);
            existingProduct.updateStock(salesProduct.getQuantity());
            productsForUpdate.add(existingProduct);
        });
        productRepository.saveAll(productsForUpdate);
        var approvedMessage = new SalesConfirmationDTO(productStock.getSalesId(), SalesStatus.APPROVED, productStock.getTransactionId());
        salesConfirmationSender.sendSalesConfirmationMessage(approvedMessage);
    }

    private void validateStockUpdateData(ProductStockDTO product) {
        if (ObjectUtils.isEmpty(product) || ObjectUtils.isEmpty(product.getSalesId()))
            throw new ValidationException("The product data and the sales ID must be informed.");

        if (ObjectUtils.isEmpty(product.getProducts())) {
            throw new ValidationException("The sales product must be informed.");
        }

        product.getProducts().forEach(salesProduct -> {
            if (ObjectUtils.isEmpty(salesProduct.getQuantity()) || ObjectUtils.isEmpty(salesProduct.getProductId())) {
                throw new ValidationException("The productID and the quantity must be informed.");
            }
        });
    }

    private void validateQuantityInStock(ProductQuantityDTO salesProduct, Product existingProduct) {
        if (BigDecimalUtil.biggerThan(salesProduct.getQuantity(), existingProduct.getQuantityAvailable())) {
            throw new ValidationException(String.format("The product %s is out of stock.", existingProduct.getId()));
        }
    }

    private void validateQuantityInStock(ProductQuantityDTO salesProduct) {
        var product = findById(salesProduct.getProductId());
        validateQuantityInStock(salesProduct, product);
    }
}
