package com.ironhack.lab_java_springboot_rest_api.exception;

public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException(String email) {
        super("Customer not found: " + email);
    }
}
