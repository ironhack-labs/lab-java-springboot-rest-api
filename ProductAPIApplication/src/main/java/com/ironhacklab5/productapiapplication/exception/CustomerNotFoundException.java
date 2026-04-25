package com.ironhacklab5.productapiapplication.exception;


/**
 * Thrown when a customer cannot be found by their email.
 * Results in a 404 Not Found response.
 */
public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException(String email) {
        super("Customer not found with email: " + email);
    }
}