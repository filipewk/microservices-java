package br.com.microservices.salesapi.config.exception;

import org.springframework.core.NestedExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class ExceptionGlobalHandler {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ExceptionDetails> handleValidationException(ValidationException exception) {
        var details = new ExceptionDetails(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
        return new ResponseEntity<>(details, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ExceptionDetails> handleConstraintViolationException(ConstraintViolationException exception) {
        var errorMessage = NestedExceptionUtils.getMostSpecificCause(exception).getMessage();
        var details = new ExceptionDetails(HttpStatus.BAD_REQUEST.value(), errorMessage);
        return new ResponseEntity<>(details, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ExceptionDetails> handleAuthenticationException(AuthenticationException exception) {
        var errorMessage = NestedExceptionUtils.getMostSpecificCause(exception).getMessage();
        var details = new ExceptionDetails(HttpStatus.UNAUTHORIZED.value(), errorMessage);
        return new ResponseEntity<>(details, HttpStatus.UNAUTHORIZED);
    }
}
