package com.fairtok.online_shopping.classes;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public class Product {
    @NotBlank(message = "Name can not be blank!")
    @Size(min=3, max=100, message = "Name can not be less 3 letters!")
    private String name;

    @PositiveOrZero
    private double price;

    @NotBlank(message = "Category can not be blank!")
    private String category;

    @PositiveOrZero
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


    public Product(String name, double price, String category, int quantity) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.quantity = quantity;
    }
}
