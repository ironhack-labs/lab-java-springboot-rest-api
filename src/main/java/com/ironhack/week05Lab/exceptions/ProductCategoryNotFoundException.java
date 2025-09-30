package com.ironhack.week05Lab.exceptions;

public class ProductCategoryNotFoundException extends RuntimeException {
    public ProductCategoryNotFoundException(String category) {
        super("Product with category: " + category + " not found");
    }
}
