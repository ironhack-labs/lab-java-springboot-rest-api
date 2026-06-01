package com.ironhack.lab.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class Product {

    @NotBlank(message = "Product name is required")
    @Size(min = 3, message = "Product name must have at least 3 characters")
    private String name;

    @Positive(message = "Price must be a positive number")
    private double price;

    @NotBlank(message = "Category is required")
    private String category;

    @Positive(message = "Quantity must be a positive number")
    private int quantity;

    public Product() {
    }

    public Product(String name, double price, String category, int quantity) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
