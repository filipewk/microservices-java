package br.com.microservices.salesapi.modules.jwt;

import br.com.microservices.salesapi.modules.jwt.dto.JwtResponse;

import javax.servlet.http.HttpServletRequest;

public interface JwtService {

    void validateAuthorization(String token);

    JwtResponse getAuthUser(String token);

    JwtResponse getUserByRequest(HttpServletRequest request);
}
