package com.springboot_lab.model;

import jakarta.validation.constraints.*;

public class Product {

    @NotBlank(message = "Product name cannot be blank")
    @Size(min = 3, message = "Product name must be at least 3 characters")
    private String name;

    @Positive(message = "Price must be positive")
    private Double price;

    @NotBlank(message = "Category cannot be blank")
    private String category;

    @Positive(message = "Quantity must be positive")
    private Integer quantity;

    // Constructors
    public Product() {
    }

    public Product(String name, Double price, String category, Integer quantity) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.quantity = quantity;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
