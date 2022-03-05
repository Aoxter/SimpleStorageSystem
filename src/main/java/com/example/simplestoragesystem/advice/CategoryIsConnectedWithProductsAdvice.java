package com.example.simplestoragesystem.advice;

import com.example.simplestoragesystem.exception.CategoryIsConnnectedWithProductsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CategoryIsConnectedWithProductsAdvice {
    @ResponseBody
    @ExceptionHandler(CategoryIsConnnectedWithProductsException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    String categoryIsConnectedWithProductsHandler(CategoryIsConnnectedWithProductsException ex) {return ex.getMessage();}
}
