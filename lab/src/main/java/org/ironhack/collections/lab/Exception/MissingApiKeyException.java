package org.ironhack.collections.lab.Exception;

public class MissingApiKeyException extends RuntimeException{
    public MissingApiKeyException(String message) {
        super(message);
    }
}
