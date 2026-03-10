package model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Product {
    @NotBlank(message="Name is required")
    @Size(min= 3,message="Name must be at least 3 characters")
    private String name;
    @Min(value = 0,message = "Only required positive number")
    private double price;
    @NotBlank(message="Category is required")
    private String category;
    @Min(value = 0,message = "Only required positive number")
    private int quantity;
    public Product() {}
    public Product(String name, double price, String category, int quantity){
        this.name=name;
        this.price=price;
        this.category=category;
        this.quantity=quantity;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getCategory() {
        return category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
