package com.workshop.week5lab.models;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.constraints.Min;

@Validated
public class Product {

    @NotBlank(message = "Name should not be blank.")
    @Length(min=3)
    private String name;

    @Min(0)
    private double price;

    @NotBlank(message = "Category can not be blank.")
    private String category;

    @Min(0)
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
}
