package br.com.microservices.productapi.modules.category.controller;

import br.com.microservices.productapi.modules.category.dto.CategoryRequest;
import br.com.microservices.productapi.modules.category.dto.CategoryResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CategoryController {

    ResponseEntity<CategoryResponse> save(CategoryRequest request);

    ResponseEntity<List<CategoryResponse>> findAll();

    ResponseEntity<CategoryResponse> findById(Long id);

    ResponseEntity<List<CategoryResponse>> findByDescription(String description);

    ResponseEntity<?> delete(Long id);

    ResponseEntity<CategoryResponse> delete(CategoryRequest request, Long id);
}
