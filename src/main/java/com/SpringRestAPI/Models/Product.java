package com.SpringRestAPI.Models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class Product {


    @NotBlank(message = "The name cannot be blank")
    @Size(min = 3, max = 12, message = "Name must be between 3 and 12 characters")
    private String productName;

    @Positive(message = "Price cannot be negative")
    private double productPrice;

    @NotNull(message = "The Category cannot be blank")
    private ProductCategories productCategory;

    @Positive(message = "Quantity cannot be negative")
    private int productQuantity;

    /// @NotBlank: Ensures that the annotated string is not null or consists of only whitespace characters.
    /// This is useful for fields like name and address to ensure that they are filled with meaningful data.


    /// @Size: Validates that the annotated string's length is within the specified range (min and max).
    /// This helps enforce constraints on text fields, such as ensuring a name is neither too short nor too long.

    /// @Positive: Ensures that the annotated number is strictly greater than 0.
    /// This is useful for values that must always be positive, such as quantities, prices, or IDs.

    public Product(String productName, double productPrice, ProductCategories productCategory, int productQuantity) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productCategory = productCategory;
        this.productQuantity = productQuantity;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public ProductCategories getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategories productCategory) {
        this.productCategory = productCategory;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

}
