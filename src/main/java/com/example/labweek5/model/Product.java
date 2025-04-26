package com.example.labweek5.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;

public class Product {

    @NotBlank(message = "Name cannot be blank")
    @Min(value = 3, message = "Name must be at least 3 characters long")
    private String name;

    @Positive(message = "Price must be a positive number")
    private double price;

    @NotBlank(message = "Category cannot be blank")
    private String category;

    @Positive(message = "Quantity must be a positive number")
    private int quantity;

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

    public Product(String name, double price, String category, int quantity) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.quantity = quantity;
    }

}
