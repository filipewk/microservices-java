package br.com.microservices.salesapi.config.interceptor;


import br.com.microservices.salesapi.config.exception.ValidationException;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

public class FeignClientAuthInterceptor implements RequestInterceptor {

    public static final String AUTHORIZATION = "Authorization";

    @Override
    public void apply(RequestTemplate template) {
        template.header(AUTHORIZATION, getCurrentRequest().getHeader(AUTHORIZATION));
    }

    private HttpServletRequest getCurrentRequest() {
        try {
            var servletRequestAttributes = (ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes());
            return  servletRequestAttributes.getRequest();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ValidationException("The current request could not be processed.");
        }
    }
}
