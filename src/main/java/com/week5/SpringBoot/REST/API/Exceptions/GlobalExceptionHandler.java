package com.week5.SpringBoot.REST.API.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException; // Wichtig für Validierung!
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 1. Validierungsfehler abfangen (Name zu kurz, Alter < 18 etc.)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        // Wir gehen alle Fehler durch und speichern Feldname + Nachricht
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));

        Logger.getLogger("ValidationError").warning("Validation failed for a request");
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    // 2. Produkt nicht gefunden
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> handleProductNotFound(ProductNotFoundException ex) {
        Logger.getLogger("ErrorLog").warning(ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    // 3. API-Key fehlt oder ist falsch
    @ExceptionHandler(MissingApiKeyException.class)
    public ResponseEntity<String> handleMissingKey(MissingApiKeyException ex) {
        Logger.getLogger("SecurityLog").warning(ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    // 4. Ungültiger Preisbereich
    @ExceptionHandler(InvalidPriceRangeException.class)
    public ResponseEntity<String> handleInvalidPrice(InvalidPriceRangeException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
