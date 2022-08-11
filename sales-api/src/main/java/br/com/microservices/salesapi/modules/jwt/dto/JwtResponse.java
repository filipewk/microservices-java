package br.com.microservices.salesapi.modules.jwt.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class JwtResponse {

    private Integer id;
    private String name;
    private String email;

    public static JwtResponse getUser(Claims jwtClaims) {
        try {
            return new ObjectMapper().convertValue(jwtClaims.get("user-details"), JwtResponse.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
