package br.com.microservices.salesapi.modules.sales.dto;

import br.com.microservices.salesapi.modules.sales.model.Order;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
public class SalesResponse {

    private List<Long> salesIds;

    public static SalesResponse ofList(List<Order> orders) {
        return SalesResponse.builder()
                .salesIds(orders.stream()
                        .map(Order::getId)
                        .collect(Collectors.toList()))
                .build();
    }
}
