package com.example.simplestoragesystem;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ProducerNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(ProducerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String orderNotFoundHandler(ProducerNotFoundException ex) {
        return ex.getMessage();
    }
}
