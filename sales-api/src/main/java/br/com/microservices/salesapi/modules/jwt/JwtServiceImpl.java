package br.com.microservices.salesapi.modules.jwt;

import br.com.microservices.salesapi.config.exception.AuthenticationException;
import br.com.microservices.salesapi.modules.jwt.dto.JwtResponse;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class JwtServiceImpl implements JwtService {

    @Value("${app-config.secrets.api-secret}")
    public String jwtSecret;

    private static final String EMPTY_SPACE = " ";
    private static final Integer TOKEN_INDEX = 1;

    @Override
    public void validateAuthorization(String token) {
        var user = getAuthUser(token);
        if (ObjectUtils.isEmpty(user) || ObjectUtils.isEmpty(user.getId())) {
            throw new AuthenticationException("The user is not valid.");
        }
    }

    @Override
    public JwtResponse getAuthUser(String token) {
        var accessToken = extractToken(token);
        try {
            var claims = Jwts
                    .parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(jwtSecret.getBytes()))
                    .build()
                    .parseClaimsJws(accessToken)
                    .getBody();
            return JwtResponse.getUser(claims);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new AuthenticationException("Error while trying to proccess the Access Token.");
        }
    }

    @Override
    public JwtResponse getUserByRequest(HttpServletRequest request) {
        var token = request.getHeader("Authorization");
        return getAuthUser(token);
    }

    private String extractToken(String token) {
        if (ObjectUtils.isEmpty(token))
            throw new AuthenticationException("The access token was not informed.");

        if (token.contains(EMPTY_SPACE))
            return token.split(EMPTY_SPACE)[TOKEN_INDEX];

        return token;
    }
}
