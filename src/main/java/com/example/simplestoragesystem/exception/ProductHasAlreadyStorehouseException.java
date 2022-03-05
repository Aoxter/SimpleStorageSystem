package com.example.simplestoragesystem.exception;

public class ProductHasAlreadyStorehouseException extends RuntimeException{
    public ProductHasAlreadyStorehouseException(final Long productId, final Long storehouseId) {
        super(String.format("Product with id %s has already storehouse with id %s. Instead try change storehouse operation", productId, storehouseId));
    }
}
