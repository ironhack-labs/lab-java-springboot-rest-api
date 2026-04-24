package com.ironhack.labjavaspringbootrestapi.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class Product {

    @NotBlank(message ="Product name cannot be blank")
    @Size(min = 3, message = "Product name must have al least 3 characters")
    private String name;

    @Positive(message = "Price must be grater than 0")
    private double price;

    @NotBlank(message = "Category cannot be blank")
    private String category;

    @Positive(message = "Quantity must be grater than 0")
    private int quantity;

    //Constructor vacio
    public Product() {
    }

    //Constructor con todos los parametros
    public Product(String name, double price, String category, int quantity){
        this.name = name;
        this.price = price;
        this.category = category;
        this.quantity = quantity;
    }

    //Getters y setters

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
