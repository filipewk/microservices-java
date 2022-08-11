package br.com.microservices.productapi.modules.category.service;

import br.com.microservices.productapi.config.exception.ValidationException;
import br.com.microservices.productapi.modules.category.dto.CategoryRequest;
import br.com.microservices.productapi.modules.category.model.Category;
import br.com.microservices.productapi.modules.category.repository.CategoryRepository;
import br.com.microservices.productapi.modules.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    @Override
    public Category save(CategoryRequest request) {
        validateCategoryNameInformed(request);
        return categoryRepository.save(Category.of(request));
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ValidationException("There's no category for the given ID."));
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Category> findByDescription(String description) {
        if (description.isBlank())
            throw new ValidationException("The Category description cannot be blank.");
        return categoryRepository.findByDescriptionIgnoreCaseContaining(description);
    }

    @Override
    public void delete(Long id) {
        if (productRepository.existsByCategoryId(id))
            throw new ValidationException("You cannot delete this category because it's already defined by a product");
        categoryRepository.deleteById(id);
    }

    @Override
    public Category update(CategoryRequest request, Long id) {
        var category = Category.of(request);
        category.setId(id);
        return categoryRepository.save(category);
    }

    private void validateCategoryNameInformed(CategoryRequest request) {
        if (ObjectUtils.isEmpty(request.getDescription()))
            throw new ValidationException("The Category description was not informed.");
    }
}
