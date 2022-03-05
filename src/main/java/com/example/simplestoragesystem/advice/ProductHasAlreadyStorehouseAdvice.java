package com.example.simplestoragesystem.advice;

import com.example.simplestoragesystem.exception.ProductHasAlreadyStorehouseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ProductHasAlreadyStorehouseAdvice {
    @ResponseBody
    @ExceptionHandler(ProductHasAlreadyStorehouseException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    String productHasAlreadyStorehouseHandler(ProductHasAlreadyStorehouseException ex) {
        return ex.getMessage();
    }
}
