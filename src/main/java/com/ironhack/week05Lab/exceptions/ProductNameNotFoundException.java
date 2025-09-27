package com.ironhack.week05Lab.exceptions;

public class ProductNameNotFoundException extends RuntimeException {
    public ProductNameNotFoundException(String name) {
        super("Product with name: " + name + " not found");
    }
}
