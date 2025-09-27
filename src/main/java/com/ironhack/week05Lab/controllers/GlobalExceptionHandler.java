package com.ironhack.week05Lab.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, List<String>>>
    handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, List<String>> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors()
            .forEach(error -> {
                String field = error.getField();
                String message = error.getDefaultMessage();
                errors.computeIfAbsent(field, k -> new ArrayList<>()).add(message);
            });

        return ResponseEntity.badRequest().body(errors);
    }
//    @ExceptionHandler(ResourceNotFoundException.class)
//    public ResponseEntity<ErrorResponse> handleResourceNotFound(ResourceNotFoundException ex) {
//        ErrorResponse error =
//        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
//    }

    // Add handlers for:
    // - Validation errors
    // - Business logic exceptions
    // - Security exceptions
    // - Unexpected errors
}
