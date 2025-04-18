package com.ironhack.springbootrestapi.model;

import java.math.BigDecimal;

public class Product {
    private String name;
    private BigDecimal price;
    private String category;
    private int quantity;

    public Product(String name, BigDecimal price, String category, int quantity) {
        setName(name);
        setPrice(price);
        setCategory(category);
        setQuantity(quantity);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name == null || name.trim().length() < 3) {
            throw new IllegalArgumentException("Name must be at least 3 characters long");
        } else {
            this.name = name;
        }
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        if(price == null || price.compareTo(new BigDecimal(0)) < 0) {
            System.out.println("Price must be greater than zero");
        } else {
            this.price = price;
        }
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        if(category == null || category.trim().length() == 0) {
            throw new IllegalArgumentException("Category cannot be blank");
        } else {
            this.category = category;
        }
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if(quantity < 0) {
            throw new IllegalArgumentException("Quantity must be at least zero");
        } else {
            this.quantity = quantity;
        }
    }
}
