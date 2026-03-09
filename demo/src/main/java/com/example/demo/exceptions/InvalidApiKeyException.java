package com.example.demo.exceptions;

public class InvalidApiKeyException extends RuntimeException {

    public InvalidApiKeyException(String message) {
        super(message);
    }
}