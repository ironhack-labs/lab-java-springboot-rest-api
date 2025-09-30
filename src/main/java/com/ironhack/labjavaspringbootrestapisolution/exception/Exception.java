package com.ironhack.labjavaspringbootrestapisolution.exception;

public class Exception {


    // Product not found
    public static class ProductNotFoundException extends RuntimeException {
        public ProductNotFoundException(String message) {
            super(message);
        }
    }

    // Invalid price range
    public static class InvalidPriceRangeException extends RuntimeException {
        public InvalidPriceRangeException(String message) {
            super(message);
        }
    }

    // Missing or invalid API key
    public static class ApiKeyMissingException extends RuntimeException {
        public ApiKeyMissingException(String message) {
            super(message);
        }
    }

}
