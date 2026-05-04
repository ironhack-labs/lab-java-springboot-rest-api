package com.example.hellolab5;

import jakarta.validation.constraints.*;

public class Product {
    @NotBlank @Size(min = 3)
    private String name;

    @Positive
    private double price;

    @NotBlank
    private String category;

    @Positive
    private int quantity;

    // Costruttore senza argomenti
    public Product() {}

    // Costruttore completo
    public Product(String name, double price, String category, int quantity) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.quantity = quantity;
    }

    // Getter e Setter
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}