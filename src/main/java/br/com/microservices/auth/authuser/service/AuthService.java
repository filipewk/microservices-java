package br.com.microservices.auth.authuser.service;

import br.com.microservices.auth.authuser.dto.AuthRequest;
import br.com.microservices.auth.authuser.dto.AuthResponse;

public interface AuthService {

    AuthResponse auth(AuthRequest request);
}
