package com.pop.kar.spring_web_jpa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.time.Instant;

@ControllerAdvice
@RestController
public class ExceptionHandlingController {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handlerAllExceptions(Exception exception, WebRequest request){
        ExceptionResponse exceptionResponse = new ExceptionResponse(Instant.now(), exception.getMessage(), request.getDescription(false));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionResponse);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handlerUserNotFoundException(Exception exception, WebRequest request){
        ExceptionResponse exceptionResponse = new ExceptionResponse(Instant.now(), exception.getMessage(), request.getDescription(false));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);
    }

    @ExceptionHandler(PostNotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handlerPostNotFoundException(Exception exception, WebRequest request){
        ExceptionResponse exceptionResponse = new ExceptionResponse(Instant.now(), exception.getMessage(), request.getDescription(false));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<ExceptionResponse> handlerMethodArgumentValidException(MethodArgumentNotValidException exception, WebRequest request){
        var errors = exception.getFieldErrors().stream()
                .map(fieldError -> "Field: " + fieldError.getField() +  " Error: " + fieldError.getDefaultMessage()).toList();
        ExceptionResponse exceptionResponse = new ExceptionResponse(Instant.now(), errors.toString(), request.getDescription(false));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }
}