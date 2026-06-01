package com.ironhack.lab.exception;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(String name) {
        super("Product not found: " + name);
    }
}
