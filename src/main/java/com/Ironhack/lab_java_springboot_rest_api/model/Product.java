package com.Ironhack.lab_java_springboot_rest_api.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class Product {
    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(min = 3, message = "El nombre debe tener al menos 3 caracteres")
    private String name;

    @Positive(message = "El precio debe ser un número positivo")
    private double price;

    @NotBlank(message = "La categoría no puede estar vacía")
    private String category;

    @Positive(message = "La cantidad debe ser un número positivo")
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