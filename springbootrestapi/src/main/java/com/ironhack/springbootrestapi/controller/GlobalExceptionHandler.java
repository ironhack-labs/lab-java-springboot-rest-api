package com.ironhack.springbootrestapi.controller;

import com.ironhack.springbootrestapi.exception.CustomerNotFound;
import com.ironhack.springbootrestapi.exception.InvalidAPIKey;
import com.ironhack.springbootrestapi.exception.InvalidCustomerInput;
import com.ironhack.springbootrestapi.exception.ProductNotFound;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFound.class)
    public ResponseEntity<?> handleProductsNotFound(ProductNotFound e) {
        return ResponseEntity.status(404).body(e.getMessage());
    }

    @ExceptionHandler(InvalidAPIKey.class)
    public ResponseEntity<?> handleInvalidAPIKey(InvalidAPIKey e) {
        return ResponseEntity.status(401).body(e.getMessage());
    }

    @ExceptionHandler(InvalidCustomerInput.class)
    public ResponseEntity<?> handleInvalidCustomerInput(InvalidCustomerInput e) {
        return ResponseEntity.status(400).body(e.getMessage());
    }

    @ExceptionHandler(CustomerNotFound.class)
    public ResponseEntity<?> handleCustomerNotFound(CustomerNotFound e) {
        return ResponseEntity.status(404).body(e.getMessage());
    }
}
