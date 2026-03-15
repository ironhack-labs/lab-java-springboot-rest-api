package az.restapi.demo.models;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class Product {

    private int counter = 1;
    private int productId;

    @NotBlank(message = "Name is required")
    @Size(min = 3, message = "Name must be at least 3 characters")
    private String name;

    @Positive
    private double price;

    @NotBlank(message = "Category must be required.")
    private String category;

    @Positive
    private int quantity;

    

    public Product(String name, double price, String category, int quantity) {
        this.productId = counter;
        this.name = name;
        this.price = price;
        this.category = category;
        this.quantity = quantity;

        counter++;
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



    public int getProductId() {
        return productId;
    }



    public void setProductId(int productId) {
        this.productId = productId;
    }


    
}
