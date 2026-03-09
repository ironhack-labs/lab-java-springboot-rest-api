package com.example.demo.exceptions;


public class InvalidPriceRangeException extends RuntimeException {

    public InvalidPriceRangeException(String message) {
        super(message);
    }
}