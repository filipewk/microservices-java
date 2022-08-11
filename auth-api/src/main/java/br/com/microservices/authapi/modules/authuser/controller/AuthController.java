package br.com.microservices.authapi.modules.authuser.controller;

import br.com.microservices.authapi.modules.authuser.dto.AuthRequest;
import br.com.microservices.authapi.modules.authuser.dto.AuthResponse;
import org.springframework.http.ResponseEntity;

public interface AuthController {

    ResponseEntity<AuthResponse> auth(AuthRequest request);
}
