package com.ironhack.lab_java_springboot_rest_api.exception;

public class MissingApiKeyException extends RuntimeException {

    public MissingApiKeyException() {
        super("Invalid or missing API-Key");
    }
}
