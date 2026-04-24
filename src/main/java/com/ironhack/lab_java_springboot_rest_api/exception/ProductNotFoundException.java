package com.ironhack.lab_java_springboot_rest_api.exception;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(String name) {
        super("Product not found: " + name);
    }
}
