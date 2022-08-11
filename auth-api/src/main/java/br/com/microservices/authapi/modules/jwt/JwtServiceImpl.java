package br.com.microservices.authapi.modules.jwt;

import br.com.microservices.authapi.exception.AuthenticationException;
import br.com.microservices.authapi.modules.jwt.dto.JwtResponse;
import br.com.microservices.authapi.modules.user.dto.UserResponse;
import br.com.microservices.authapi.modules.user.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class JwtServiceImpl implements JwtService {

    @Value("${app-config.secrets.api-secret}")
    public String jwtSecret;

    private static final String EMPTY_SPACE = " ";
    private static final Integer TOKEN_INDEX = 1;

    public String getToken(User user) {
        var secretKey = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
        return Jwts.builder()
                .setId(UUID.randomUUID().toString())
                .setSubject(user.getName())
                .setExpiration(
                        Date.from(
                                LocalDateTime.now().plusMinutes(15L)
                                        .atZone(ZoneId.systemDefault())
                                        .toInstant()))
                .signWith(secretKey, SignatureAlgorithm.HS512)
                .claim("user-details", UserResponse.of(user))
                .compact();
    }

    @Override
    public void validateAuthorization(String token) {
        var accessToken = extractToken(token);
        try {
            var claims = Jwts
                    .parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(jwtSecret.getBytes()))
                    .build()
                    .parseClaimsJws(accessToken)
                    .getBody();
            var user = JwtResponse.getUser(claims);
            if (ObjectUtils.isEmpty(user) || ObjectUtils.isEmpty(user.getId())) {
                throw new AuthenticationException("The user is not valid.");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new AuthenticationException("Error while trying to proccess the Access Token.");
        }
    }

    private String extractToken(String token) {
        if (ObjectUtils.isEmpty(token))
            throw new AuthenticationException("The access token was not informed.");

        if (token.contains(EMPTY_SPACE))
            return token.split(EMPTY_SPACE)[TOKEN_INDEX];

        return token;
    }
}
