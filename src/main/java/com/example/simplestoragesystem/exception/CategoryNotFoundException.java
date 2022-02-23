package com.example.simplestoragesystem.exception;

public class CategoryNotFoundException extends RuntimeException{
    public CategoryNotFoundException(final Long id) { super(String.format("Could not find category %s", id)); }
}
