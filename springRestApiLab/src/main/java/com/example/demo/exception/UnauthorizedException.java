package com.example.demo.exception;

/**
 * Custom exception thrown when the API-Key is missing or invalid.
 * This exception is thrown from all controllers and handled globally
 * with HTTP 401 UNAUTHORIZED status for cleaner separation of concerns.
 */
public class UnauthorizedException extends RuntimeException {

    /**
     * Constructor with custom message.
     *
     * @param message The exception message.
     */
    public UnauthorizedException(String message) {
        super(message);
    }
}