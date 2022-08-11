package br.com.microservices.productapi.utils;

import br.com.microservices.productapi.config.exception.ValidationException;
import lombok.experimental.UtilityClass;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@UtilityClass
public class RequestUtil {

    public HttpServletRequest getCurrentRequest() {
        try {
            var servletRequestAttributes = (ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes());
            return servletRequestAttributes.getRequest();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ValidationException("The current request could not be processed.");
        }
    }
}
