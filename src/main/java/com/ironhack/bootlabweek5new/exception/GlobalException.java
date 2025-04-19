package com.ironhack.bootlabweek5new.exception;

public class GlobalException extends RuntimeException {

    public ValidationErrorsException(String message) {
        super(message);
    }

    public MissingApiKeyExceptionException(String message) {
        super(message);
    }

    public ProductNotFoundException(String message) {
        super(message);
    }


    public InvalidPriceRangeException(String message) {
        super(message);
    }

}
