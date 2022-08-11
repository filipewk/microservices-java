package br.com.microservices.productapi.modules.category.controller;

import br.com.microservices.productapi.modules.category.dto.CategoryRequest;
import br.com.microservices.productapi.modules.category.dto.CategoryResponse;
import br.com.microservices.productapi.modules.category.service.CategoryService;
import br.com.microservices.productapi.modules.supplier.dto.SupplierRequest;
import br.com.microservices.productapi.modules.supplier.dto.SupplierResponse;
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
@RequestMapping("category")
@RequiredArgsConstructor
public class CategoryControllerImpl implements CategoryController {

    private final CategoryService categoryService;

    @Override
    @PostMapping
    public ResponseEntity<CategoryResponse> save(@RequestBody CategoryRequest request) {
        var category = categoryService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(CategoryResponse.of(category));
    }

    @Override
    @GetMapping
    public ResponseEntity<List<CategoryResponse>> findAll() {
        var categories = categoryService.findAll();
        return ResponseEntity.ok(CategoryResponse.ofList(categories));
    }

    @Override
    @GetMapping("{id}")
    public ResponseEntity<CategoryResponse> findById(@PathVariable Long id) {
        var category = categoryService.findById(id);
        return ResponseEntity.ok(CategoryResponse.of(category));
    }

    @Override
    @GetMapping("description/{description}")
    public ResponseEntity<List<CategoryResponse>> findByDescription(@PathVariable String description) {
        var categories = categoryService.findByDescription(description);
        return ResponseEntity.ok(CategoryResponse.ofList(categories));
    }

    @Override
    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    @PutMapping("{id}")
    public ResponseEntity<CategoryResponse> delete(@RequestBody CategoryRequest request, @PathVariable Long id) {
        var supplier = categoryService.update(request, id);
        return ResponseEntity.ok(CategoryResponse.of(supplier));
    }
}
