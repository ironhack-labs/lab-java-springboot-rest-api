package com.example.Labs5.Service;

import com.example.Labs5.Product.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public class ProductService {

    private List<Product> products = new ArrayList<>(List.of(
            new Product("Laptop", 999.99, "Electronics", 10),
            new Product("Smartphone", 699.99, "Electronics", 20),
            new Product("Desk Chair", 89.99, "Furniture", 15),
            new Product("Water Bottle", 19.99, "Accessories", 50)
    ));



    public void addProduct(Product product) {
        products.add(product);
        if (product.getName() == null || product.getName().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be null or empty");
        } else if (product.getPrice() <= 0) {
            throw new IllegalArgumentException("Product price must be a positive number");
        } else if (product.getCategory() == null || product.getCategory().isEmpty()) {
            throw new IllegalArgumentException("Product category cannot be null or empty");
        } else if (product.getQuantity() < 0) {
            throw new IllegalArgumentException("Product quantity cannot be negative");
        }
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(products);
    }

    public Product getProductByName(String name) {
        return products.stream()
                .filter(product -> product.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    public void updateProduct(String name, Product updatedProduct) {
        Product existingProduct = getProductByName(name);
        if (existingProduct != null) {
            existingProduct.setPrice(updatedProduct.getPrice());
            existingProduct.setCategory(updatedProduct.getCategory());
            existingProduct.setQuantity(updatedProduct.getQuantity());
        }
    }

    public void deleteProduct(String name) {
        products.removeIf(product -> product.getName().equalsIgnoreCase(name));
    }

    public List<Product> getProductsByCategory(String category) {
        return products.stream()
                .filter(product -> product.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    public List<Product> getProductsByPriceRange(double min, double max) {
        return products.stream()
                .filter(product -> product.getPrice() >= min && product.getPrice() <= max)
                .collect(Collectors.toList());
    }
}