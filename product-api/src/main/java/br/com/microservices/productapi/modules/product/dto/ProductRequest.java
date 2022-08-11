package br.com.microservices.productapi.modules.product.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductRequest {

    private String name;
    private BigDecimal quantityAvailable;
    private Long categoryId;
    private Long supplierId;
}
