package com.ironhacklab5.productapiapplication.exception;

/**
 * Thrown when the min price is greater than the max price in a price range query.
 * Results in a 400 Bad Request response.
 */
public class InvalidPriceRangeException extends RuntimeException {

    public InvalidPriceRangeException(double min, double max) {
        super("Invalid price range: min (" + min + ") cannot be greater than max (" + max + ")");
    }
}