package br.com.microservices.authapi.modules.authuser.service;

import br.com.microservices.authapi.modules.authuser.dto.AuthRequest;
import br.com.microservices.authapi.modules.authuser.dto.AuthResponse;

public interface AuthService {

    AuthResponse auth(AuthRequest request);
}
