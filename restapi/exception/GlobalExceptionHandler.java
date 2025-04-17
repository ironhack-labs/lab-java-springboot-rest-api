package com.ironhack.restapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Handle validation errors (e.g., @NotNull, @Min, etc.)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    // Handle missing API-Key header
    @ExceptionHandler(MissingApiKeyException.class)
    public ResponseEntity<Map<String, String>> handleMissingApiKeyException() {
        Map<String, String> response = new HashMap<>();
        response.put("error", "API-Key header is missing");
        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }

    // Handle product not found
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleProductNotFoundException(ProductNotFoundException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("error", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    // Handle invalid price range (for example, when price is negative)
    @ExceptionHandler(InvalidPriceRangeException.class)
    public ResponseEntity<Map<String, String>> handleInvalidPriceRangeException() {
        Map<String, String> response = new HashMap<>();
        response.put("error", "Invalid price range");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // Handle generic exceptions
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, String> handleGenericException(Exception ex) {
        Map<String, String> response = new HashMap<>();
        response.put("error", "An unexpected error occurred");
        response.put("message", ex.getMessage());
        return response;
    }
}