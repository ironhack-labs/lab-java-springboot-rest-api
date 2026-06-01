package com.workshop.week5lab;

public class InvalidPriceRangeException extends RuntimeException {

    public InvalidPriceRangeException() {
        super("Invalid price range: min must be less than or equal to max");
    }
}