package com.example.za.Models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class Product {
    @NotBlank(message = "Name cannot be blank")
    @Size(min=3,message = "Name's size cannot be more than 3")
    private String name;

    @Positive(message = "Price must be positive")
    private double price;
    @NotBlank(message = "Category cannot be blank")
    private String Category;
    @Positive(message = "Price must be positive")
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
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product(String name, double price, int quantity, String category) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        Category = category;
    }
}