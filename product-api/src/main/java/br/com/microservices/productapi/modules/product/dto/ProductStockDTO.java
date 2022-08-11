package br.com.microservices.productapi.modules.product.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class ProductStockDTO {

    private Long salesId;
    private List<ProductQuantityDTO> products;
    private String transactionId;
}
