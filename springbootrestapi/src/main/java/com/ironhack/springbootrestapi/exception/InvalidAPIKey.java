package com.ironhack.springbootrestapi.exception;

public class InvalidAPIKey extends RuntimeException {
    public InvalidAPIKey(String message) {
        super(message);
    }
}
