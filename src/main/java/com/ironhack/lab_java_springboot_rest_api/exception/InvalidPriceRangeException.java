package com.ironhack.lab_java_springboot_rest_api.exception;

public class InvalidPriceRangeException extends RuntimeException {

    public InvalidPriceRangeException() {
        super("Invalid price range: min cannot be greater than max");
    }
}
