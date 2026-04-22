package com.SpringRestAPI.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

/// @ControllerAdvice provides a centralized mechanism to handle exceptions across all your REST controllers.
/// It allows you to define ways to handle specific exception types and craft consistent, meaningful responses.

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> handleProductNotFound(ProductNotFoundException ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MissingApiKeyException.class)
    public ResponseEntity<String> handleMissingApiKey(MissingApiKeyException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class) // For product and customer model validations
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        /// Happens when Spring validates your request body or parameters
        /// and finds problems (like a @NotNull or @Min annotation failing)

        Map<String, String> errors = new HashMap<>(); /// Instead of returning just one error,
        /// you might have multiple fields failing, so we use a HashMap to return each field and its error message.

        ex.getBindingResult().getAllErrors().forEach(error -> { /// gives all validation errors from the request.
            String fieldName = ((FieldError) error).getField(); /// Cast each error to FieldError so we can get the field name.
            String errorMessage = error.getDefaultMessage(); /// Get the default message
            errors.put(fieldName, errorMessage); /// Put it in the Map with fieldName as key and errorMessage as value.
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        ///  Return all errors as a JSON object with 400 Bad Request
    }

    ///  If a field fails validation, Spring creates a FieldError object.
    /// FieldError contains info about:

    /// field: the name of the field that failed (name or price)
    /// rejectedValue: the value the user sent
    /// defaultMessage: the message you wrote in the annotation

    /// getDefaultMessage() returns the validation message you wrote in the annotation

    @ExceptionHandler(InvalidPriceRangeException.class)
    public ResponseEntity<String> handleInvalidPriceRange(InvalidPriceRangeException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }


}