package com.example.simplestoragesystem.exception;

public class StorehouseNotFoundException extends RuntimeException{
    public StorehouseNotFoundException(final Long id) {super(String.format("Could not find storehouse %s", id)); }
}
