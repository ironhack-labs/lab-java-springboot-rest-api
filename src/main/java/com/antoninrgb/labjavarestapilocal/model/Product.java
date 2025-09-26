/*
Create a Product class with the following validated properties:
name (not blank, min length 3)
price (positive number)
category (not blank)
quantity (positive number)
*/

package com.antoninrgb.labjavarestapilocal.model;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class Product {

    @Size(min = 3, message = "Name cannot be empty)")
    String name;

    @Positive(message = "Price cannot be negative")
    double price;

    @Size(min = 1, message = "Category cannot be empty")
    String category;

    @Positive(message = "Quantity must be greater than 0")
    int quantity;

    public Product(String name, double price, String category, int quantity) {
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

    public String getInfo() {
        return "Name: " + name + ", price: " + price +  ", category: " + category + ", quantity: " + quantity;
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
