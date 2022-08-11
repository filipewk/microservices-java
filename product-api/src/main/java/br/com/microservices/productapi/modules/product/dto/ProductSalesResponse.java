package br.com.microservices.productapi.modules.product.dto;

import br.com.microservices.productapi.modules.category.dto.CategoryResponse;
import br.com.microservices.productapi.modules.product.model.Product;
import br.com.microservices.productapi.modules.supplier.dto.SupplierResponse;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class ProductSalesResponse {

    private Long id;
    private String name;
    private BigDecimal quantityAvailable;
    private SupplierResponse supplier;
    private CategoryResponse category;
    @JsonProperty("created_at")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime createdAt;
    private List<Long> sales;


    public static ProductSalesResponse of(Product product, List<Long> salesIds) {
        return ProductSalesResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .quantityAvailable(product.getQuantityAvailable())
                .createdAt(product.getCreatedAt())
                .supplier(SupplierResponse.of(product.getSupplier()))
                .category(CategoryResponse.of(product.getCategory()))
                .sales(salesIds)
                .build();
    }
}
