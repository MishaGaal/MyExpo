package com.example.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;


@Slf4j
@ControllerAdvice
public class UserExceptionHandler {


    @ExceptionHandler(value = Exception.class)
    public String handleUserException(HttpServletRequest req, Exception e) {
        log.error("URL Request failed " + req.getRequestURL() + ": " + e.getMessage() + ": " + e.getCause());
        return "error";
    }
}
