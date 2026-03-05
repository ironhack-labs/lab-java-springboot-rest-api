package com.example.demo.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

/**
 * Represents a Product entity with validation constraints.
 * This class models a product with name, price, category, and quantity.
 */
public class Product {

    @NotBlank(message = "Name cannot be blank")
    @Size(min = 3, message = "Name must be at least 3 characters long")
    private String name;

    @Positive(message = "Price must be a positive number")
    private double price;

    @NotBlank(message = "Category cannot be blank")
    private String category;

    @Positive(message = "Quantity must be greater than zero")
    private int quantity;

    /**
     * Constructor to initialize a Product object.
     *
     * @param name     The name of the product.
     * @param price    The price of the product.
     * @param category The category of the product.
     * @param quantity The quantity of the product.
     */
    public Product(String name, double price, String category, int quantity) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.quantity = quantity;
    }

    // Getters

    /**
     * Gets the name of the product.
     *
     * @return The product name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the price of the product.
     *
     * @return The product price.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Gets the category of the product.
     *
     * @return The product category.
     */
    public String getCategory() {
        return category;
    }

    /**
     * Gets the quantity of the product.
     *
     * @return The product quantity.
     */
    public int getQuantity() {
        return quantity;
    }

    // Setters

    /**
     * Sets the name of the product.
     *
     * @param name The new name for the product.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the price of the product.
     *
     * @param price The new price for the product.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Sets the category of the product.
     *
     * @param category The new category for the product.
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Sets the quantity of the product.
     *
     * @param quantity The new quantity for the product.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}