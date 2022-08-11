package br.com.microservices.productapi.modules.product.controller;

import br.com.microservices.productapi.modules.product.dto.ProductCheckStockRequest;
import br.com.microservices.productapi.modules.product.dto.ProductRequest;
import br.com.microservices.productapi.modules.product.dto.ProductResponse;
import br.com.microservices.productapi.modules.product.dto.ProductSalesResponse;
import br.com.microservices.productapi.modules.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("product")
@RequiredArgsConstructor
public class ProductControllerImpl implements ProductController {

    private final ProductService productService;

    @Override
    @PostMapping
    public ResponseEntity<ProductResponse> save(@RequestBody ProductRequest request) {
        var product = productService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(ProductResponse.of(product));
    }

    @Override
    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAll() {
        var products = productService.findAll();
        return ResponseEntity.ok(ProductResponse.ofList(products));
    }

    @Override
    @GetMapping("{id}")
    public ResponseEntity<ProductResponse> findById(@PathVariable Long id) {
        var product = productService.findById(id);
        return ResponseEntity.ok(ProductResponse.of(product));
    }

    @Override
    @GetMapping("name/{name}")
    public ResponseEntity<List<ProductResponse>> findByName(@PathVariable String name) {
        var product = productService.findByName(name);
        return ResponseEntity.ok(ProductResponse.ofList(product));
    }

    @Override
    @GetMapping("category/{categoryId}")
    public ResponseEntity<List<ProductResponse>> findByCategoryId(@PathVariable Long categoryId) {
        var products = productService.findByCategoryId(categoryId);
        return ResponseEntity.ok(ProductResponse.ofList(products));
    }

    @Override
    @GetMapping("supplier/{supplierId}")
    public ResponseEntity<List<ProductResponse>> findBySupplierId(@PathVariable Long supplierId) {
        var products = productService.findBySupplierId(supplierId);
        return ResponseEntity.ok(ProductResponse.ofList(products));
    }

    @Override
    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    @PutMapping("{id}")
    public ResponseEntity<ProductResponse> delete(@RequestBody ProductRequest request, @PathVariable Long id) {
        var supplier = productService.update(request, id);
        return ResponseEntity.ok(ProductResponse.of(supplier));
    }

    @Override
    @PostMapping("check-stock")
    public ResponseEntity<?> checkProductStock(@RequestBody ProductCheckStockRequest request) {
        productService.checkProductStock(request);
        return ResponseEntity.noContent().build();
    }


    @Override
    @GetMapping("{id}/sales")
    public ProductSalesResponse findByProductSales(@PathVariable Long id) {
        return productService.findProductSales(id);
    }
}
