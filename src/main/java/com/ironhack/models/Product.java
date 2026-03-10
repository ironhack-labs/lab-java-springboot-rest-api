package com.ironhack.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class Product {
    @NotBlank(message = "Name cannot be blank")
    @Size(min = 3, message = "Name must be at least 3 caracters long")
    private String name;

    @Positive(message = "Price must be a positive number")
    private double price;

    @NotBlank(message = "Quantity must be a positive number")
    private String category;

    @Positive(message = "Guality must be a positive number")
    private int quantity;

    public Product(String name, double price, String category, int quantity){
        this.name = name;
        this.price = price;
        this.category = category;
        this.quantity =quantity;
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
