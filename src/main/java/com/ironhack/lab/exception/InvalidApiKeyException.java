package com.ironhack.lab.exception;

public class InvalidApiKeyException extends RuntimeException {

    public InvalidApiKeyException() {
        super("Invalid API-Key header");
    }
}
