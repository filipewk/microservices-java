package br.com.microservices.productapi.modules.product.controller;

import br.com.microservices.productapi.modules.product.dto.ProductCheckStockRequest;
import br.com.microservices.productapi.modules.product.dto.ProductRequest;
import br.com.microservices.productapi.modules.product.dto.ProductResponse;
import br.com.microservices.productapi.modules.product.dto.ProductSalesResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductController {

    ResponseEntity<ProductResponse> save(ProductRequest request);

    ResponseEntity<List<ProductResponse>> findAll();

    ResponseEntity<ProductResponse> findById(Long id);

    ResponseEntity<List<ProductResponse>> findByName(String name);

    ResponseEntity<List<ProductResponse>> findByCategoryId(Long categoryId);

    ResponseEntity<List<ProductResponse>> findBySupplierId(Long supplierId);

    ResponseEntity<?> delete(Long id);

    ResponseEntity<ProductResponse> delete(ProductRequest request, Long id);

    ProductSalesResponse findByProductSales(Long id);

    ResponseEntity<?> checkProductStock(ProductCheckStockRequest request);
}
