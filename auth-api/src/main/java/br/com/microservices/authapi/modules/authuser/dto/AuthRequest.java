package br.com.microservices.authapi.modules.authuser.dto;

import lombok.Getter;

@Getter
public class AuthRequest {

    private String email;
    private String password;
}
