package br.com.microservices.authapi.statusapi.controller;

import br.com.microservices.authapi.statusapi.dto.StatusResponse;
import org.springframework.http.ResponseEntity;

public interface StatusController {

    ResponseEntity<StatusResponse> getApiStatus();
}
