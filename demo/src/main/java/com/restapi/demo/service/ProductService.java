package com.restapi.demo.service;

import com.restapi.demo.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final List<Product> products = new ArrayList<>();

    // Add a new product
    public Product addProduct(Product product) {
        products.add(product);
        return product;
    }

    // Get all products
    public List<Product> getAllProducts() {
        return new ArrayList<>(products);
    }

    // Get product by name
    public Product getProductByName(String name) {
        return products.stream()
                .filter(p -> p.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Product not found with name: " + name));
    }

    // Update product
    public Product updateProduct(String name, Product updatedProduct) {
        Product existing = getProductByName(name);
        existing.setName(updatedProduct.getName());
        existing.setPrice(updatedProduct.getPrice());
        existing.setCategory(updatedProduct.getCategory());
        existing.setQuantity(updatedProduct.getQuantity());
        return existing;
    }

    // Delete product
    public void deleteProduct(String name) {
        Product product = getProductByName(name);
        products.remove(product);
    }

    // Get products by category
    public List<Product> getProductsByCategory(String category) {
        return products.stream()
                .filter(p -> p.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    // Get products by price range
    public List<Product> getProductsByPriceRange(double min, double max) {
        if (min < 0 || max < 0) {
            throw new RuntimeException("Price values must not be negative");
        }
        if (min > max) {
            throw new RuntimeException("Minimum price must not be greater than maximum price");
        }
        return products.stream()
                .filter(p -> p.getPrice() >= min && p.getPrice() <= max)
                .collect(Collectors.toList());
    }
}