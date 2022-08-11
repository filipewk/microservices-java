package br.com.microservices.salesapi.modules.sales.dto;

import br.com.microservices.salesapi.modules.product.dto.ProductDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderRequest {

    private List<ProductDTO> products;
}
