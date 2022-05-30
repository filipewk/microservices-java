package br.com.microservices.auth.authuser.dto;

import lombok.Getter;

@Getter
public class AuthRequest {

    private String email;
    private String password;
}
