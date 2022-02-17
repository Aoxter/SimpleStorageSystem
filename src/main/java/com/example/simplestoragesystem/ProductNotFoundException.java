package com.example.simplestoragesystem;

class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(Long id) {
        super(String.format("Could not find product %s", id));
    }
}
