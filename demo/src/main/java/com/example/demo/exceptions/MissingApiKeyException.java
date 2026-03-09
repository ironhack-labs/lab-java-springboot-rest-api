package com.example.demo.exceptions;

public class MissingApiKeyException extends RuntimeException {

    public MissingApiKeyException(String message) {
        super(message);
    }

}