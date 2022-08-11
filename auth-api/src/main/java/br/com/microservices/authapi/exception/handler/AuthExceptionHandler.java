package br.com.microservices.authapi.exception.handler;

import br.com.microservices.authapi.exception.ExceptionResponse;
import br.com.microservices.authapi.exception.UserNotFoundException;
import br.com.microservices.authapi.exception.UserUnauthorizedException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class AuthExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleAllException(Exception ex, WebRequest request) {
        return new ResponseEntity<>(ExceptionResponse.mountException(ex, request), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ExceptionResponse> handleConstraintViolation(Exception ex, WebRequest request) {
        return new ResponseEntity<>(ExceptionResponse.mountException(ex, request), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ExceptionResponse> handleDataIntegrityViolation(Exception ex, WebRequest request) {
        return new ResponseEntity<>(ExceptionResponse.mountException(ex, request), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleUserNotFoundException(Exception ex, WebRequest request) {
        return new ResponseEntity<>(ExceptionResponse.mountException(ex, request), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserUnauthorizedException.class)
    public ResponseEntity<ExceptionResponse> handleUserUnauthorizedException(Exception ex, WebRequest request) {
        return new ResponseEntity<>(ExceptionResponse.mountException(ex, request), HttpStatus.UNAUTHORIZED);
    }
}
