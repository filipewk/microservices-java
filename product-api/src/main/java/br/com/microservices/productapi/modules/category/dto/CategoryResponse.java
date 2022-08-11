package br.com.microservices.productapi.modules.category.dto;

import br.com.microservices.productapi.modules.category.model.Category;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class CategoryResponse {

    private Long id;
    private String description;

    public static CategoryResponse of(Category category) {
        var response = new CategoryResponse();
        BeanUtils.copyProperties(category, response);
        return response;
    }

    public static List<CategoryResponse> ofList(List<Category> categories) {
        return categories.stream()
                .map(CategoryResponse::of)
                .collect(Collectors.toList());
    }
}
