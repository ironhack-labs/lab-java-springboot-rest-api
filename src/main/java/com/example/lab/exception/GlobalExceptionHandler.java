package com.example.lab.exception;

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
    public ResponseEntity<Map<String, String>> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(err -> errors.put(err.getField(), err.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<Map<String, String>> handleMissingHeader(MissingRequestHeaderException ex) {
        Map<String, String> resp = new HashMap<>();
        resp.put("error", "Missing API-Key header");
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(resp);
    }

    @ExceptionHandler(ApiKeyException.class)
    public ResponseEntity<Map<String, String>> handleApiKey(ApiKeyException ex) {
        Map<String, String> resp = new HashMap<>();
        resp.put("error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(resp);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Map<String, String>> handleNotFound(NotFoundException ex) {
        Map<String, String> resp = new HashMap<>();
        resp.put("error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resp);
    }

    @ExceptionHandler(InvalidPriceRangeException.class)
    public ResponseEntity<Map<String, String>> handleInvalidRange(InvalidPriceRangeException ex) {
        Map<String, String> resp = new HashMap<>();
        resp.put("error", ex.getMessage());
        return ResponseEntity.badRequest().body(resp);
    }
}
