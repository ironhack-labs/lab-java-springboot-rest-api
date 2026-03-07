package org.ironhack.collections.lab.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleProductNotFound(ProductNotFoundException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(InvalidPriceRangeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handlerInvalidPriceRange(InvalidPriceRangeException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(MissingApiKeyException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
        public String handlerMissingApiKey(MissingApiKeyException ex){
        return ex.getMessage();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handlerGeneral(Exception ex) {
        return ex.getMessage();
    }

}
