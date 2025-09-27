package com.ironhack.labjavaspringbootrestapisolutions.controllers;

import com.ironhack.labjavaspringbootrestapisolutions.exception.ProductException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GeneralControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, List<String>>> handleValidationErrors(
            MethodArgumentNotValidException ex) {

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


    @ExceptionHandler(ProductException.class)
    public ResponseEntity<String> ProductExceptionHandler(ProductException ex) {
        return ResponseEntity.status(404).body(ex.getMessage());
    }



}
