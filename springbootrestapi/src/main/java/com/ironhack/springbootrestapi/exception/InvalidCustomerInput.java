package com.ironhack.springbootrestapi.exception;

public class InvalidCustomerInput extends RuntimeException {
    public InvalidCustomerInput(String message) {
        super(message);
    }
}
