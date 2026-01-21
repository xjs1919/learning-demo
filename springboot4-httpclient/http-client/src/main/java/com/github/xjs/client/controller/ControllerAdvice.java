package com.github.xjs.client.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.RestClientException;
@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(RestClientException.class)
    public String handleHttpException(RestClientException exception){
        return exception.getMessage();
    }
}
