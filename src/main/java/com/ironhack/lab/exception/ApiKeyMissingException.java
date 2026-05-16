package com.ironhack.lab.exception;

public class ApiKeyMissingException extends RuntimeException {
    public ApiKeyMissingException(String message) {
        super(message);
    }
}

