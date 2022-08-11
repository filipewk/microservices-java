package br.com.microservices.authapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UserUnauthorizedException extends RuntimeException {

    public UserUnauthorizedException() {
        super("Email or Password doesn't match.");
    }
}
