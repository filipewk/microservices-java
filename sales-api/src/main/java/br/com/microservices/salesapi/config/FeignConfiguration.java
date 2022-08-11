package br.com.microservices.salesapi.config;

import br.com.microservices.salesapi.config.exception.ValidationException;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

public class FeignConfiguration implements RequestInterceptor {

    public static final String AUTHORIZATION = "Authorization";
    private static final String TRANSACTION_ID = "transactionid";

    @Override
    public void apply(RequestTemplate template) {
        template
                .header(AUTHORIZATION, getCurrentRequest().getHeader(AUTHORIZATION))
                .header(TRANSACTION_ID, getCurrentRequest().getHeader(TRANSACTION_ID));
    }

    private HttpServletRequest getCurrentRequest() {
        try {
            var servletRequestAttributes = (ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes());
            return servletRequestAttributes.getRequest();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ValidationException("The current request could not be processed.");
        }
    }
}
