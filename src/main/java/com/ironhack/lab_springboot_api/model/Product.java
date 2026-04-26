package com.ironhack.lab_springboot_api.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class Product {
    @NotBlank(message = "El nombre no puede estar vacío")
    @Size (min = 3, message = "Mínimo 3 caracteres")
    private String name;

    @Positive(message = "El Precio debe ser positivo")
    private double price;

    @NotBlank(message = "La categoria es obligatoria")
    private String category;

    @Positive(message = "La cantidad debe ser mayor a 0")
    private int quantity;

    public Product(String name, double price, String category, int quantity) {
        setName(name);
        setPrice(price);
        setCategory(category);
        setQuantity(quantity);
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
