package com.example.lab.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class Product {
    @NotBlank(message = "Name must not be blank")
    @Size(min = 3, message = "Name must be at least 3 characters")
    private String name;

    @Min(value = 1, message = "Price must be positive")
    private double price;

    @NotBlank(message = "Category must not be blank")
    private String category;

    @Min(value = 1, message = "Quantity must be positive")
    private int quantity;

    public Product() {}

    public Product(String name, double price, String category, int quantity) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.quantity = quantity;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}
