package com.ironhack.week05Lab.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.UUID;

/*
Create a Product class with the following validated properties:

name (not blank, min length 3)
price (positive number)
category (not blank)
quantity (positive number)
 */
public class Product {
    private final UUID id = UUID.randomUUID();

    @NotBlank(message = "Name must not be blank")
    @Size(min = 3, message = "Name must be at least 3 characters!")
    private String name;

    @Min(0)
    private double price;

    @NotBlank(message = "Category must not be blank")
    private String category;

    @Min(1)
    private int quantity;

    public UUID getId() {
        return id;
    }

    public Product() {}

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

    public String getCategory() {
        return category;
    }

    public void setPrice(double price) {
        this.price = price;
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
