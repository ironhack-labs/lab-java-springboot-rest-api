package com.ironhack.lab3.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ResourceNotFoundException ex) {
        ErrorResponse error = new ErrorResponse(HttpStatus.NOT_FOUND.value(),
                "Not Found",ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
}



    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ErrorResponse> handleDuplicate(DuplicateResourceException ex) {
        ErrorResponse error = new ErrorResponse(HttpStatus.CONFLICT.value(),
                "Conflict",ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

//   handleValidationError
@ExceptionHandler(MethodArgumentNotValidException.class)
public ResponseEntity<ErrorResponse> handleValidationError(MethodArgumentNotValidException ex){

    String message = ex.getBindingResult()
            .getFieldError()
            .getDefaultMessage();

    ErrorResponse error = new ErrorResponse(
            HttpStatus.BAD_REQUEST.value(),
            "Validation Error",
            message
    );

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
}

//   handleProductNotFound
@ExceptionHandler(ProductNotFoundException.class)
public ResponseEntity<ErrorResponse> handleProductNotFound(ProductNotFoundException ex){
    ErrorResponse error = new ErrorResponse(
            HttpStatus.NOT_FOUND.value(),
            "Product Not Found",
            ex.getMessage()
    );
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
}
//   handleMissingApiKey
@ExceptionHandler(MissingRequestHeaderException.class)
public ResponseEntity<ErrorResponse> handleMissingApiKey(MissingRequestHeaderException ex){

    ErrorResponse error = new ErrorResponse(
            HttpStatus.BAD_REQUEST.value(),
            "Missing Header",
            "API-Key header is required"
    );

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
}

//   handleInvalidPriceRange
@ExceptionHandler(InvalidPriceRangeException.class)
public ResponseEntity<ErrorResponse> handleInvalidPriceRange(InvalidPriceRangeException ex){

    ErrorResponse error = new ErrorResponse(
            HttpStatus.BAD_REQUEST.value(),
            "Invalid Price Range",
            ex.getMessage()
    );

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
}

}
