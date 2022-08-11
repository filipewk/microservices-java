package br.com.microservices.salesapi.modules.product.dto;

import br.com.microservices.salesapi.modules.sales.model.Order;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class ProductStockDTO {

    private Long salesId;
    private List<ProductDTO> products;

    public static ProductStockDTO of(Order order) {
        return ProductStockDTO.builder()
                .salesId(order.getId())
                .products(order.getProducts())
                .build();
    }
}
