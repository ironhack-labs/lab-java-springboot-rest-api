package com.ironhack.api.model;

import jakarta.validation.constraints.*;

public class Product {
    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(min = 3, message = "El nombre debe tener al menos 3 caracteres")
    private String name;

    @Positive(message = "El precio debe ser positivo")
    private Double price;

    @NotBlank(message = "La categoría no puede estar vacía")
    private String category;

    @Positive(message = "La cantidad debe ser positiva")
    private Integer quantity;

    public Product() {}

    public Product(String name, Double price, String category, Integer quantity) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.quantity = quantity;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
}
