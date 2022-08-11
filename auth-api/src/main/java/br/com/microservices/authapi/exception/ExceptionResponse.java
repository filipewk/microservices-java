package br.com.microservices.authapi.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ExceptionResponse {

    private LocalDateTime timestamp;
    private String message;
    private String details;

    public static ExceptionResponse mountException(Exception ex, WebRequest request) {
        var errorMessage = NestedExceptionUtils.getMostSpecificCause(ex).getMessage();
        return new ExceptionResponse(LocalDateTime.now(), errorMessage, request.getDescription(false));
    }
}
