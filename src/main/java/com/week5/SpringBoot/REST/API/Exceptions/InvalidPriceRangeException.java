package com.week5.SpringBoot.REST.API.Exceptions;

public class InvalidPriceRangeException extends RuntimeException {
    public InvalidPriceRangeException(String message) {
        super(message);
    }

}
