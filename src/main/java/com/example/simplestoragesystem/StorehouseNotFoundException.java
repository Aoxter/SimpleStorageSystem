package com.example.simplestoragesystem;

public class StorehouseNotFoundException extends RuntimeException{
    public StorehouseNotFoundException(Long id) {super(String.format("Could not find storehouse %s", id)); }
}
