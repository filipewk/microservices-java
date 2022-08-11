package br.com.microservices.productapi.modules.category.service;

import br.com.microservices.productapi.modules.category.dto.CategoryRequest;
import br.com.microservices.productapi.modules.category.model.Category;

import java.util.List;

public interface CategoryService {

    Category save(CategoryRequest request);

    Category findById(Long id);

    List<Category> findAll();

    List<Category> findByDescription(String description);

    void delete(Long id);

    Category update(CategoryRequest request, Long id);
}
