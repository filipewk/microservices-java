package br.com.microservices.auth.utils.jwt;

import br.com.microservices.auth.user.model.User;

public interface JwtService {

    String getToken(User user);

    void validateAuthorization(String token);
}
