package com.amir.ecommerce.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Objects;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponse> resourceNotFound(ResourceNotFoundException e){
        ExceptionResponse response = new ExceptionResponse()
                .setErrorCode(HttpStatus.NOT_FOUND)
                .setErrorMessage(e.getMessage())
                .setTimestamp(LocalDateTime.now());

        return  new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ExceptionResponse> responseStatus(ResponseStatusException e){
        ExceptionResponse response = new ExceptionResponse()
                .setErrorCode(e.getStatus())
                .setErrorMessage(e.getLocalizedMessage())
                .setTimestamp(LocalDateTime.now());

        return new ResponseEntity<>(response, e.getStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> methodArgumentNotValid(MethodArgumentNotValidException e){
        ExceptionResponse response = new ExceptionResponse()
                .setErrorCode(HttpStatus.BAD_REQUEST)
                .setErrorMessage(Objects.requireNonNull(e.getFieldError()).getDefaultMessage())
                .setTimestamp(LocalDateTime.now());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ExceptionResponse> authentication(AuthenticationException e){
        ExceptionResponse response = new ExceptionResponse()
                .setErrorCode(HttpStatus.UNAUTHORIZED)
                .setErrorMessage("Invalid login credentials")
                .setTimestamp(LocalDateTime.now());

        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<ExceptionResponse> dateTimeParse(DateTimeParseException e){
        ExceptionResponse response = new ExceptionResponse()
                .setErrorCode(HttpStatus.BAD_REQUEST)
                .setErrorMessage(e.getMessage())
                .setTimestamp(LocalDateTime.now());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}

