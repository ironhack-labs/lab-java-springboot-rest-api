package com.workshop.week5lab;

public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException(String email) {
        super("Customer not found: " + email);
    }
}
