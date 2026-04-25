package com.ironhacklab5.productapiapplication.exception;


/**
 * Thrown when the "API-Key" header is present but its value is incorrect.
 * Results in a 403 Forbidden response.
 */
public class InvalidApiKeyException extends RuntimeException {

    public InvalidApiKeyException() {
        super("Invalid API key — access denied");
    }
}