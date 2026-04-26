package com.week5.SpringBoot.REST.API.Exceptions;

public class MissingApiKeyException extends RuntimeException {
    public MissingApiKeyException(String message) {
        super(message);
    }
}
