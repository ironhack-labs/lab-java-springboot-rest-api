package com.ironhack.lab.handler;

import com.ironhack.lab.exception.CustomerNotFoundException;
import com.ironhack.lab.exception.InvalidApiKeyException;
import com.ironhack.lab.exception.InvalidPriceRangeException;
import com.ironhack.lab.exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationErrors(MethodArgumentNotValidException exception) {
        Map<String, String> validationErrors = new HashMap<>();

        exception.getBindingResult().getFieldErrors().forEach(error ->
                validationErrors.put(error.getField(), error.getDefaultMessage()));

        return buildErrorResponse(HttpStatus.BAD_REQUEST, "Validation failed", validationErrors);
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<Map<String, Object>> handleMissingHeader(MissingRequestHeaderException exception) {
        return buildErrorResponse(
                HttpStatus.UNAUTHORIZED,
                "Missing required header: " + exception.getHeaderName(),
                null);
    }

    @ExceptionHandler(InvalidApiKeyException.class)
    public ResponseEntity<Map<String, Object>> handleInvalidApiKey(InvalidApiKeyException exception) {
        return buildErrorResponse(HttpStatus.UNAUTHORIZED, exception.getMessage(), null);
    }

    @ExceptionHandler({ProductNotFoundException.class, CustomerNotFoundException.class})
    public ResponseEntity<Map<String, Object>> handleNotFound(RuntimeException exception) {
        return buildErrorResponse(HttpStatus.NOT_FOUND, exception.getMessage(), null);
    }

    @ExceptionHandler(InvalidPriceRangeException.class)
    public ResponseEntity<Map<String, Object>> handleInvalidPriceRange(InvalidPriceRangeException exception) {
        return buildErrorResponse(HttpStatus.BAD_REQUEST, exception.getMessage(), null);
    }

    private ResponseEntity<Map<String, Object>> buildErrorResponse(
            HttpStatus status,
            String message,
            Map<String, String> validationErrors) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", status.value());
        response.put("error", status.getReasonPhrase());
        response.put("message", message);

        if (validationErrors != null) {
            response.put("validationErrors", validationErrors);
        }

        return ResponseEntity.status(status).body(response);
    }
}
