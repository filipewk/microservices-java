package br.com.microservices.authapi.modules.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {

    private String name;
    private String email;
    private String password;
}
