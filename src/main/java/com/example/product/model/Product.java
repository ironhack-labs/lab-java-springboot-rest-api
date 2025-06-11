package com.example.product.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.List;

public class Product {

    @NotBlank(message = "Name must not be blank")
    @Size(min = 3, message = "Name must be at least 3 characters long")
    private String name;

    @Positive(message = "Price must be a positive number")
    private double price;

    @NotBlank(message = "Category must not be blank")
    private String category;

    @Min(value = 1, message = "Quantity must be at least 1")
    private int quantity;

    private List<ProductItem> productItems;

    public List<ProductItem> getProductItems() {
        return productItems;
    }

    public Product(String name,
                   double price,
                   String category,
                   int quantity,
                   List<ProductItem> productItems) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.quantity = quantity;
        this.productItems = productItems;
    }

    public void setProductItems(List<ProductItem> productItems) {
        this.productItems = productItems;
    }

    // Constructors
    public Product() {
    }

//    public Product(String name, double price, String category, int quantity) {
//        this.name = name;
//        this.price = price;
//        this.category = category;
//        this.quantity = quantity;
//    }

    // Getters and Setters

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
