package com.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserExceptionHandler {

    HttpStatus bad = HttpStatus.BAD_REQUEST;

    @ExceptionHandler
    public ResponseEntity handleUserException(UserException e) {
        return new ResponseEntity(e, bad);
    }
}
