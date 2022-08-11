package br.com.microservices.productapi.config.interceptor;

import br.com.microservices.productapi.config.exception.ValidationException;
import br.com.microservices.productapi.modules.jwt.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    private static final String AUTHORIZATION = "Authorization";
    private static final String TRANSACTION_ID = "transactionid";

    @Autowired
    private JwtService jwtService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        if (ObjectUtils.isEmpty(request.getHeader(TRANSACTION_ID)))
            throw new ValidationException("The transactionid header is required.");

        var authorization = request.getHeader(AUTHORIZATION);
        jwtService.validateAuthorization(authorization);

        request.setAttribute("serviceid", UUID.randomUUID().toString());
        return true;
    }
}
