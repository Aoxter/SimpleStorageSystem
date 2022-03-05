package com.example.simplestoragesystem.advice;

import com.example.simplestoragesystem.exception.StorehouseIsConnectedWithProductsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class StorehouseIsConnectedWithProductsAdvice {
    @ResponseBody
    @ExceptionHandler(StorehouseIsConnectedWithProductsException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    String storehouseIsConnectedWithProductsHandler(StorehouseIsConnectedWithProductsException ex){return ex.getMessage();}
}
