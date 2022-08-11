package br.com.microservices.authapi.statusapi.dto;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class StatusResponse {

    private final String service = "Auth-API-java";
    private final String status = "up";
    private final Integer httpStatus = HttpStatus.OK.value();
}
