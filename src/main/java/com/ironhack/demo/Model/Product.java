package com.ironhack.demo.Model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class Product {
@NotBlank
    private String name;
@Positive
    private double price;
@NotBlank
    private String catogery;
    @Positive
    private int quanity;

    public Product(String name, double price, String catogery, int quanity) {
        this.name = name;
        this.price = price;
        this.catogery = catogery;
        this.quanity = quanity;
    }

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

    public String getCatogery() {
        return catogery;
    }

    public void setCatogery(String catogery) {
        this.catogery = catogery;
    }

    public int getQuanity() {
        return quanity;
    }

    public void setQuanity(int quanity) {
        this.quanity = quanity;
    }
}
