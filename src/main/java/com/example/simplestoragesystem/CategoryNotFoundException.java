package com.example.simplestoragesystem;

public class CategoryNotFoundException extends RuntimeException{
    public CategoryNotFoundException(Long id) { super(String.format("Could not find category %s", id)); }
}
