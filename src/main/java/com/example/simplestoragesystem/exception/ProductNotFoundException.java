package com.example.simplestoragesystem.exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(final Long id) {
        super(String.format("Could not find product %s", id));
    }
}
