package com.example.demo.exception;

import org.springframework.aot.hint.RuntimeHints;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {



    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleValidationErrors(MethodArgumentNotValidException ex){
        Map<String, String> errors=new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()) );
        return errors;
    }


    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)

    public Map<String, String> missingApiKey(RuntimeException ex){
        Map<String, String> error=new HashMap<>();

        error.put("error", ex.getMessage());
        return error;
    }


    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)

    public Map<String, String> handleProductNotFoundError(ProductNotFoundException ex){
        Map<String, String> error=new HashMap<>();
        error.put("error", ex.getMessage());
        return  error;
    }


    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)

    public Map<String, String> handleInvalidPriceRange(IllegalArgumentException ex){
        Map<String, String> error=new HashMap<>();
        error.put("error", ex.getMessage());
        return  error;
    }
}
