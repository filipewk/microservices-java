package br.com.microservices.productapi.modules.product.service;

import br.com.microservices.productapi.modules.product.dto.ProductCheckStockRequest;
import br.com.microservices.productapi.modules.product.dto.ProductRequest;
import br.com.microservices.productapi.modules.product.dto.ProductSalesResponse;
import br.com.microservices.productapi.modules.product.dto.ProductStockDTO;
import br.com.microservices.productapi.modules.product.model.Product;

import java.util.List;

public interface ProductService {

    Product save(ProductRequest request);

    Product findById(Long id);

    List<Product> findAll();

    List<Product> findByName(String name);

    List<Product> findByCategoryId(Long categoryId);

    List<Product> findBySupplierId(Long supplierId);

    Boolean existsByCategoryId(Long categoryId);

    Boolean existsBySupplierId(Long supplierId);

    void delete(Long id);

    Product update(ProductRequest request, Long id);

    void updateProductStock(ProductStockDTO product);

    ProductSalesResponse findProductSales(Long id);

    void checkProductStock(ProductCheckStockRequest request);
}
