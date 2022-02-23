package com.example.simplestoragesystem.exception;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(final Long id) {
        super(String.format("Could not find order %s", id));
    }
}
