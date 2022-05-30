package br.com.microservices.auth.authuser.controller;

import br.com.microservices.auth.authuser.dto.AuthRequest;
import br.com.microservices.auth.authuser.dto.AuthResponse;
import br.com.microservices.auth.authuser.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController {

    private final AuthService authService;

    @Override
    @PostMapping
    public ResponseEntity<AuthResponse> auth(@RequestBody AuthRequest request) {
        var authResponse = authService.auth(request);
        return ResponseEntity.ok().body(authResponse);
    }
}
