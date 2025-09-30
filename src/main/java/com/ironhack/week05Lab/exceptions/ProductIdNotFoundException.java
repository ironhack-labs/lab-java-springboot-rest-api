package com.ironhack.week05Lab.exceptions;

import java.util.UUID;

public class ProductIdNotFoundException extends RuntimeException {
    public ProductIdNotFoundException(UUID id) {
        super("Product with id: " + id + " not found");
    }
}
