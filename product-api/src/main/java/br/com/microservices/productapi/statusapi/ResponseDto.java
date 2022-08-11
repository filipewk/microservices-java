package br.com.microservices.productapi.statusapi;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ResponseDto {

    private String service;
    private String status;
    private Integer httpStatus;
}
