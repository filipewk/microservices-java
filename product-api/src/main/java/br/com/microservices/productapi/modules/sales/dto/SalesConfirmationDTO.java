package br.com.microservices.productapi.modules.sales.dto;

import br.com.microservices.productapi.modules.sales.enums.SalesStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SalesConfirmationDTO {

    private Long salesId;
    private SalesStatus status;
    private String transactionId;
}
