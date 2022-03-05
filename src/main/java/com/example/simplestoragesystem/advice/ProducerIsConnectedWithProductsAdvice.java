package com.example.simplestoragesystem.advice;

import com.example.simplestoragesystem.exception.ProducerIsConnnectedWithProductsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ProducerIsConnectedWithProductsAdvice {
    @ResponseBody
    @ExceptionHandler(ProducerIsConnnectedWithProductsException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    String producerIsConnectedWithProductsHandler(ProducerIsConnnectedWithProductsException ex) {
        return ex.getMessage();
    }
}
