package com.example.LAB.SpringBoot.REST.API.Models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;


public class Product {
//    name (not blank, min length 3)
//    price (positive number)
//    category (not blank)
//    quantity (positive number)
    @Size(min = 3, message = "Name should have a minimum 3 characters")
    private String  name;

    @Positive(message = "Price must be positive")
    private double price;

    @NotEmpty(message = "Category shouldnt be empty")
    private String category;

    @PositiveOrZero(message = "Quantity must be positive")
    private int quantity;

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }
}
