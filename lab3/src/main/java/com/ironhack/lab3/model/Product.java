package com.ironhack.lab3.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Product {

    @NotBlank(message = "Name required")
    @Size(min = 3, message = "Name must be minimum 3 characters")
    private String name;

    @NotBlank(message = "Price required")
    @Min(0)
    private double price;

    @NotBlank(message = "Category required")
    private String category;

    @NotBlank(message = "Quantity required")
    @Min(0)
    private int quantity;

    private Long id;

    public Product() {}

    //constructor
    public Product(String name, double price,String category,int quantity) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.quantity = quantity;
    }

    //getters and setters

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public double getPrice() {return price;}
    public void setPrice(double price) {this.price = price;}
    public String getCategory() {return category;}
    public void setCategory(String category) {this.category = category;}
    public int getQuantity() {return quantity;}
    public void setQuantity(int quantity) {this.quantity = quantity;}

    public void getId() {this.id = id;}
}
