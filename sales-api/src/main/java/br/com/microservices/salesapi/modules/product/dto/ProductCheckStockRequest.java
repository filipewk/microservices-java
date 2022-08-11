package br.com.microservices.salesapi.modules.product.dto;

import br.com.microservices.salesapi.modules.sales.model.Order;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class ProductCheckStockRequest {

    List<ProductDTO> products;

    public static ProductCheckStockRequest of(Order order) {
        return ProductCheckStockRequest.builder()
                .products(order.getProducts())
                .build();
    }
}
