package br.com.microservices.productapi.modules.product.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductQuantityDTO {

    private Long productId;
    private BigDecimal quantity;
}
