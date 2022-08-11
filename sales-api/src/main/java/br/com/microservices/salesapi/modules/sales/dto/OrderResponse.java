package br.com.microservices.salesapi.modules.sales.dto;

import br.com.microservices.salesapi.modules.product.dto.ProductDTO;
import br.com.microservices.salesapi.modules.sales.enums.OrderStatus;
import br.com.microservices.salesapi.modules.sales.model.Order;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
public class OrderResponse {

    private Long id;
    private List<ProductDTO> products;
    private Long userId;
    private OrderStatus status;


    public static OrderResponse of(Order order) {
        return OrderResponse.builder()
                .id(order.getId())
                .products(order.getProducts())
                .userId(order.getUserId())
                .status(order.getStatus())
                .build();
    }

    public static List<OrderResponse> ofList(List<Order> orders) {
        return orders.stream()
                .map(OrderResponse::of)
                .collect(Collectors.toList());
    }
}
