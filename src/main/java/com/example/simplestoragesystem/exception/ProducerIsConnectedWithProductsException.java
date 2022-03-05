package com.example.simplestoragesystem.exception;

public class ProducerIsConnectedWithProductsException extends RuntimeException{
    public ProducerIsConnectedWithProductsException(final Long id) {
        super(String.format("Producer %s, is connected with some products. First delete these products", id));
    }
}
