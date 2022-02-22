package com.example.simplestoragesystem;

public class ProducerNotFoundException extends RuntimeException{
    public ProducerNotFoundException(Long id) {
        super(String.format("Could not find producer %s", id));
    }
}
