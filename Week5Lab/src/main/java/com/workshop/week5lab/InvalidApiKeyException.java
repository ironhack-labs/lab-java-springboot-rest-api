package com.workshop.week5lab;

public class InvalidApiKeyException extends RuntimeException {

    public InvalidApiKeyException() {
        super("Invalid API-Key header");
    }
}