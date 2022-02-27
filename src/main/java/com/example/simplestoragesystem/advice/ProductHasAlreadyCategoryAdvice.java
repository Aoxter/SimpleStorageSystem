package com.example.simplestoragesystem.advice;

import com.example.simplestoragesystem.exception.ProductHasAlreadyCategoryException;
import com.example.simplestoragesystem.exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ProductHasAlreadyCategoryAdvice {
    @ResponseBody
    @ExceptionHandler(ProductHasAlreadyCategoryException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String productHasAlreadyCategoryHandler(ProductHasAlreadyCategoryException ex) {
        return ex.getMessage();
    }
}
