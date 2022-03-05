package com.example.simplestoragesystem.exception;

public class ProducerIsConnnectedWithProductsException extends RuntimeException{
    public ProducerIsConnnectedWithProductsException(final Long id) {
        super(String.format("Producer %s, is connected with some products. First delete these products", id));
    }
}
