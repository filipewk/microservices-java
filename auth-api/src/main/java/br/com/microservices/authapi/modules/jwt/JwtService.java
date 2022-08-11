package br.com.microservices.authapi.modules.jwt;

import br.com.microservices.authapi.modules.user.model.User;

public interface JwtService {

    String getToken(User user);

    void validateAuthorization(String token);
}
