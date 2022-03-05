package com.example.simplestoragesystem.exception;

public class StorehouseIsConnectedWithProductsException extends RuntimeException{
    public StorehouseIsConnectedWithProductsException(Long id) {
        super(String.format("Storehouse %s, is connected with some products. First delete these products", id));
    }
}
