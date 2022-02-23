package com.example.simplestoragesystem.advice;

import com.example.simplestoragesystem.exception.StorehouseNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
public class StorehouseNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(StorehouseNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String storehouseNotFoundHandler(StorehouseNotFoundException ex) { return ex.getMessage(); }
}
