package com.example.springbootrestapi.exception;

public class InvalidRange extends RuntimeException {
    public InvalidRange(String message) {
        super(message);
    }
}
