package com.ironhack.labweek5.model;

//Create a Product class with the following validated properties:
//
//name (not blank, min length 3)
//price (positive number)
//category (not blank)
//quantity (positive number)


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class Product {
    //@Min for numbers
    @NotBlank(message = "Cannot be empty") @Size(min = 3, message = "Minimum length: 3 characters")
    private String name;

    @Positive(message = "Must be bigger than 0")
    private double price;

    @NotBlank(message = "Cannot be empty")
    private String category;

    @Positive(message = "Must be bigger than 0")
    private int quantity;

    //getter and setters
    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public double getPrice(){
        return price;
    }

    public void setPrice(double price){
        this.price = price;
    }

    public String getCategory(){
        return category;
    }

    public void setCategory(String category){
        this.category = category;
    }

    public int getQuantity(){
        return quantity;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    //constructor
    public Product(String name, double price, String category, int quantity){
        this.name = name;
        this.price = price;
        this.category = category;
        this.quantity = quantity;

    }
}