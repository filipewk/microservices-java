package br.com.microservices.authapi.modules.authuser.controller;

import br.com.microservices.authapi.modules.authuser.dto.AuthRequest;
import br.com.microservices.authapi.modules.authuser.dto.AuthResponse;
import br.com.microservices.authapi.modules.authuser.service.AuthService;
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
