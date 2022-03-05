package com.example.simplestoragesystem.advice;

import com.example.simplestoragesystem.exception.ProductHasAlreadyProducerException;
import com.example.simplestoragesystem.exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ProductHasAlreadyProducerAdvice {
    @ResponseBody
    @ExceptionHandler(ProductHasAlreadyProducerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    String productHasAlreadyProducerHandler(ProductHasAlreadyProducerException ex) {
        return ex.getMessage();
    }
}
