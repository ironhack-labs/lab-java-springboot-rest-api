package com.ironhack.demo_lab.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class Product {
    private Long id;

    @NotBlank
    @Size(min = 3, message = "Name must be at least 3 characters!")
    private String name;

    @Positive
    private Double price;

    @NotBlank
    private String category;

    @Positive
    private int quantity;

    public Product(String name, Double price, String category, int quantity) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.quantity = quantity;
    }

    public Product() {
    }


    //getters
    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getCategory() {
        return category;
    }

    public Long getId() {
        return id;
    }

    //setters

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
