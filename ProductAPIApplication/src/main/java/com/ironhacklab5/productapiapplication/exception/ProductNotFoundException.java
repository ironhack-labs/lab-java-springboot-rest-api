package com.ironhacklab5.productapiapplication.exception;


/**
 * Thrown when a product cannot be found by its name.
 * Results in a 404 Not Found response.
 */
public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(String name) {
        super("Product not found with name: " + name);
    }
}