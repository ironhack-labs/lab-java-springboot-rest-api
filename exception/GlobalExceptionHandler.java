package com.ironhack.labweek5.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Validation errors (return proper error messages)
//Missing API-Key header
//Product not found
//Invalid price range

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationErrors(MethodArgumentNotValidException ex)
    {
        //String List to show all messages, String String to show last message
        Map<String, List<String>> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(error -> {
                    String field = error.getField();
                    String message = error.getDefaultMessage();
                    errors.computeIfAbsent(field, k -> new ArrayList<>())
                            .add(message);
                });
        return ResponseEntity
                .badRequest()
                .body(errors);
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<String> handleMissingHeader(MissingRequestHeaderException ex)
    {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body("Missing header: " + ex.getHeaderName());
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> handleProductNotFound(ProductNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    @ExceptionHandler(InvalidPriceRangeException.class)
    public ResponseEntity<String> handleInvalidPriceRange(InvalidPriceRangeException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }
}
