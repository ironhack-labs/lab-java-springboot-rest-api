package com.ironhack.restapi.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class Product {
    private int id;
    @NotBlank(message = "Name cannot be empty")
    @Size(min=3, message = "Name must be at least 3 characters long")
    private String name;
    @Positive(message = "Price must be a positive number")
    private double price;
    @NotBlank(message = "Category cannot be empty")
    private String category;
    @Positive(message = "Quantity must be a positive number")
    private int quantity;

    public Product(int id, String name, double price, String category, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
