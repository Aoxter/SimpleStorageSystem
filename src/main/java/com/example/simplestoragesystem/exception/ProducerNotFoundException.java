package com.example.simplestoragesystem.exception;

public class ProducerNotFoundException extends RuntimeException{
    public ProducerNotFoundException(final Long id) {
        super(String.format("Could not find producer %s", id));
    }
}
