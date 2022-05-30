package br.com.microservices.auth.authuser.controller;

import br.com.microservices.auth.authuser.dto.AuthRequest;
import br.com.microservices.auth.authuser.dto.AuthResponse;
import org.springframework.http.ResponseEntity;

public interface AuthController {

    ResponseEntity<AuthResponse> auth(AuthRequest request);
}
