package com.ironhack.lab.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidationErrors(MethodArgumentNotValidException ex, HttpServletRequest request) {
        List<String> errors = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        return new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Validation Failed",
                errors,
                request.getRequestURI()
        );
    }

    @ExceptionHandler(ApiKeyMissingException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse handleApiKeyMissing(ApiKeyMissingException ex, HttpServletRequest request) {
        return new ErrorResponse(
                HttpStatus.UNAUTHORIZED.value(),
                "Unauthorized",
                List.of(ex.getMessage()),
                request.getRequestURI()
        );
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse handleMissingHeader(MissingRequestHeaderException ex, HttpServletRequest request) {
        return new ErrorResponse(
                HttpStatus.UNAUTHORIZED.value(),
                "Unauthorized",
                List.of("Missing required header: " + ex.getHeaderName()),
                request.getRequestURI()
        );
    }

    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleProductNotFound(ProductNotFoundException ex, HttpServletRequest request) {
        return new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                "Not Found",
                List.of(ex.getMessage()),
                request.getRequestURI()
        );
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleCustomerNotFound(CustomerNotFoundException ex, HttpServletRequest request) {
        return new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                "Not Found",
                List.of(ex.getMessage()),
                request.getRequestURI()
        );
    }

    @ExceptionHandler(InvalidPriceRangeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleInvalidPriceRange(InvalidPriceRangeException ex, HttpServletRequest request) {
        return new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Bad Request",
                List.of(ex.getMessage()),
                request.getRequestURI()
        );
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleGenericException(Exception ex, HttpServletRequest request) {
        return new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Internal Server Error",
                List.of(ex.getMessage()),
                request.getRequestURI()
        );
    }
}

