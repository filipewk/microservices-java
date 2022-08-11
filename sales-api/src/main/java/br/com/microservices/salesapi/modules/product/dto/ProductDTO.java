package br.com.microservices.salesapi.modules.product.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class ProductDTO implements Serializable {

    private Long productId;
    private BigDecimal quantity;
}
