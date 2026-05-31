package com.ironhack.springbootrestapi;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String productNotFound(ProductNotFoundException exception) {
        return exception.getMessage();
    }

    @ExceptionHandler(InvalidPriceRangeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String invalidPriceRange(InvalidPriceRangeException exception) {
        return exception.getMessage();
    }

    @ExceptionHandler(MissingApiKeyException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String missingApiKey(MissingApiKeyException exception) {
        return exception.getMessage();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<String> validationError(MethodArgumentNotValidException exception) {
        List<String> errors = new ArrayList<>();

        for (int i = 0; i < exception.getBindingResult().getFieldErrors().size(); i++) {
            errors.add(exception.getBindingResult().getFieldErrors().get(i).getDefaultMessage());
        }

        return errors;
    }
    @ExceptionHandler(CustomerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String customerNotFound(CustomerNotFoundException exception) {
        return exception.getMessage();
    }
}