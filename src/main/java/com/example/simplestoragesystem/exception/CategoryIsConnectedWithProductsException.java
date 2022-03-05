package com.example.simplestoragesystem.exception;

public class CategoryIsConnectedWithProductsException extends RuntimeException {
    public CategoryIsConnectedWithProductsException(final Long id) {
        super(String.format("Category %s, is connected with some products. First delete these products", id));
    }
}
