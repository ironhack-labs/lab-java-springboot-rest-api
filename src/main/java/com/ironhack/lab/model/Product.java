package com.ironhack.lab.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class Product {

    @NotBlank(message = "Name cannot be blank")
    @Size(min = 3, message = "Name must have at least 3 characters")
    private String name;

    @Positive(message = "Price must be a positive number")
    private Double price;

    @NotBlank(message = "Category cannot be blank")
    private String category;

    @Positive(message = "Quantity must be a positive number")
    private Integer quantity;

    public Product() {
    }

    public Product(String name, Double price, String category, Integer quantity) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.quantity = quantity;
    }

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

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}

