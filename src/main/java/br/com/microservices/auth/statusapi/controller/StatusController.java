package br.com.microservices.auth.statusapi.controller;

import br.com.microservices.auth.statusapi.dto.StatusResponse;
import org.springframework.http.ResponseEntity;

public interface StatusController {

    ResponseEntity<StatusResponse> getApiStatus();
}
