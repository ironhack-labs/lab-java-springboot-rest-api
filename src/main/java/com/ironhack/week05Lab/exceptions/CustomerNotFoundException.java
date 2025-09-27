package com.ironhack.week05Lab.exceptions;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(String email) {
        super("Customer with email: " + email + " not found");
    }
}
