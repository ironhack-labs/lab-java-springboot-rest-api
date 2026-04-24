package com.ironhack.lab_java_springboot_rest_api.exception;

public class CustomerAlreadyExistsException extends RuntimeException {

    public CustomerAlreadyExistsException(String email) {
        super("A customer with email " + email + " already exists");
    }
}
