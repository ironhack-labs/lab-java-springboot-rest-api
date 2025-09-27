package com.ironhack.labjavaspringbootrestapisolutions.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.Length;

public class Product {

    // Attributes & Validations------------------------------------------------------------------
    @NotBlank(message = "The name can not be blank")
    @Length(min = 3, message = "The name must be at least 3 characters long")
    private String name;

    @Positive(message = "The price must be a positive number")
    private double price;

    @NotBlank(message = "The category can not be blank")
    private String category;

    @Positive(message = "The quantity must be a positive number")
    private int quantity;

    // Constructor------------------------------------------------------------------
    public Product(){
    }

    public Product(String name,double price,String category,int quantity){
        this.name = name;
        this.price = price;
        this.category = category;
        this.quantity = quantity;
    }

    // Getters------------------------------------------------------------------
    public String getName(){
        return name;
    }

    public double getPrice(){
        return price;
    }

    public String getCategory(){
        return category;
    }

    public int getQuantity(){
        return quantity;
    }

    // Setters------------------------------------------------------------------
    public void setName(String name){
        this.name = name;
    }

    public void setPrice(double price){
        this.price = price;
    }

    public void setCategory(String category){
        this.category = category;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString(){
        return "Product : " + getName() + "\n" +
                "Price : " + getPrice() + "\n" +
                "Category : " + getCategory() + "\n" +
                "Quantity : " + getQuantity();
    }


}
