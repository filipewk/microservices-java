package br.com.microservices.productapi.modules.sales.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class SalesProductResponse {

    private List<Long> salesIds;
}
