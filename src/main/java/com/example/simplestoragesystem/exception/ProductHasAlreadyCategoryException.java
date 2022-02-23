package com.example.simplestoragesystem.exception;

public class ProductHasAlreadyCategoryException extends RuntimeException{
    public ProductHasAlreadyCategoryException(final Long productId, final Long categoryId) {
        super(String.format("Product with id %s has already category with id %s. Instead try change category operation", productId, categoryId));
    }
}
