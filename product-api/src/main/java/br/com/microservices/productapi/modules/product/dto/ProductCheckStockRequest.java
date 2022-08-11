package br.com.microservices.productapi.modules.product.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductCheckStockRequest {

    private List<ProductQuantityDTO> products;
}
