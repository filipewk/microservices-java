package br.com.microservices.productapi.modules.jwt;

public interface JwtService {

    void validateAuthorization(String token);
}
