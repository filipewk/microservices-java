package br.com.microservices.salesapi.config.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ExceptionDetails {

    private Integer status;
    private String message;
}
