package br.com.microservices.salesapi.modules.sales.dto;

import br.com.microservices.salesapi.modules.sales.enums.OrderStatus;
import lombok.Getter;

@Getter
public class SalesConfirmationDTO {

    private Long salesId;
    private OrderStatus status;
}
