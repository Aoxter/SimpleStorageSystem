package com.example.simplestoragesystem.exception;

public class ProductHasAlreadyProducerException extends RuntimeException{
    public ProductHasAlreadyProducerException(final Long productId, final Long producerId) {
        super(String.format("Product with id %s has already producer with id %s. Instead try change producer operation", productId, producerId));
    }
}
