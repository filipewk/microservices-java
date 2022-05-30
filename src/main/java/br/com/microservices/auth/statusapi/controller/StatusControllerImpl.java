package br.com.microservices.auth.statusapi.controller;

import br.com.microservices.auth.statusapi.dto.StatusResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatusControllerImpl implements StatusController {

    @Override
    @GetMapping("status")
    public ResponseEntity<StatusResponse> getApiStatus() {
        return ResponseEntity.ok().body(new StatusResponse());
    }
}
