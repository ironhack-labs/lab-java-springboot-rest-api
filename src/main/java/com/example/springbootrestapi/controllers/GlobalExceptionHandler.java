package com.example.springbootrestapi.controllers;

import com.example.springbootrestapi.exception.InvalidRange;
import com.example.springbootrestapi.exception.ProductNotFound;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ProductNotFound.class)
    public ResponseEntity<String> handleProductNotFoundException(ProductNotFound e) {
        return ResponseEntity.status(404).body(e.getMessage());
    }
    @ExceptionHandler(InvalidRange.class)
    public ResponseEntity<String> handleInvalidRangeException(InvalidRange e) {
        return ResponseEntity.status(400).body(e.getMessage());
    }
}
