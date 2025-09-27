package com.ironhack.restapi.service;
import com.ironhack.restapi.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final List<Product> products = new ArrayList<>();

    // Add new product
    public void addProduct(Product product) {
        products.add(product);
    }

    // Get all products
    public List<Product> getAllProducts() {
        return products;
    }

    // Get product by name
    public Optional<Product> getProductByName(String name) {
        return products.stream()
                .filter(p -> p.getName().equalsIgnoreCase(name))
                .findFirst();
    }

    // Update product
    public boolean updateProduct(String name, Product updatedProduct) {
        return getProductByName(name).map(existing -> {
            existing.setName(updatedProduct.getName());
            existing.setPrice(updatedProduct.getPrice());
            existing.setCategory(updatedProduct.getCategory());
            existing.setQuantity(updatedProduct.getQuantity());
            return true;
        }).orElse(false);
    }

    // Delete product
    public boolean deleteProduct(String name) {
        return products.removeIf(p -> p.getName().equalsIgnoreCase(name));
    }

    // Get products by category
    public List<Product> getProductsByCategory(String category) {
        return products.stream()
                .filter(p -> p.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    // Get products by price range
    public List<Product> getProductsByPriceRange(double min, double max) {
        return products.stream()
                .filter(p -> p.getPrice() >= min && p.getPrice() <= max)
                .collect(Collectors.toList());
    }
}
