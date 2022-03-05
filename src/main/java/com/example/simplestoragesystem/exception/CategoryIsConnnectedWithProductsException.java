package com.example.simplestoragesystem.exception;

public class CategoryIsConnnectedWithProductsException extends RuntimeException {
    public CategoryIsConnnectedWithProductsException(final Long id) {
        super(String.format("Category %s, is connected with some products. First delete these products", id));
    }
}
