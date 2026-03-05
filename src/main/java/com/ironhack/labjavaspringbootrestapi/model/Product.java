package com.ironhack.labjavaspringbootrestapi.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class Product {
    private Long id;

    @NotBlank(message = "Name is required")
    @Min(3)
    private String name;

    @Positive(message = "Price must be positive")
    private double price;

    @NotBlank(message = "Category is required")
    private String category;

    @Positive(message = "Quantity must be positive")
    private int quantity;

    public Product() { }

    public Product(Long id, String name, String category, int quantity, double price) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.quantity = quantity;
        this.price = price;

    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
