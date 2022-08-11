package br.com.microservices.salesapi.modules.jwt.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
public class UserDTO {

    private Integer id;
    private String name;
    private String email;

    public static UserDTO of(JwtResponse jwtResponse) {
        var user = new UserDTO();
        BeanUtils.copyProperties(jwtResponse, user);
        return user;
    }
}
