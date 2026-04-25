package com.ironhacklab5.productapiapplication.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

/**
 * Represents a product in the store.
 * All fields are validated on incoming requests using Jakarta Bean Validation.
 */
public class Product {

    // Name must not be blank and must be at least 3 characters
    @NotBlank(message = "Product name must not be blank")
    @Size(min = 3, message = "Product name must be at least 3 characters")
    private String name;

    // Price must be a positive number (> 0)
    @Positive(message = "Price must be a positive number")
    private double price;

    // Category must not be blank
    @NotBlank(message = "Category must not be blank")
    private String category;

    // Quantity must be a positive number (> 0)
    @Positive(message = "Quantity must be a positive number")
    private int quantity;

    // --- Constructors ---

    public Product() {}

    public Product(String name, double price, String category, int quantity) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.quantity = quantity;
    }

    // --- Getters and Setters ---

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}