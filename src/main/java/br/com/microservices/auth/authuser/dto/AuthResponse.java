package br.com.microservices.auth.authuser.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AuthResponse {

    private String accessToken;


    public static AuthResponse of(String token) {
        return AuthResponse.builder()
                .accessToken(token)
                .build();
    }
}
