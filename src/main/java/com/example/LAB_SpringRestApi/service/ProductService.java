package com.example.LAB_SpringRestApi.service;

import com.example.LAB_SpringRestApi.model.Product;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        products.add(product);
    }

    public List<Product> getAllProducts() {
        return products;
    }

    public Optional<Product> getByName(String name) {
        return products.stream()
                .filter(p -> p.getName().equalsIgnoreCase(name))
                .findFirst();
    }

    public boolean updateProduct(String name, Product updatedProduct) {
        return getByName(name).map(p -> {
            p.setPrice(updatedProduct.getPrice());
            p.setCategory(updatedProduct.getCategory());
            p.setQuantity(updatedProduct.getQuantity());
            return true;
        }).orElse(false);
    }

    public boolean deleteProduct(String name) {
        return products.removeIf(p -> p.getName().equalsIgnoreCase(name));
    }

    public List<Product> getByCategory(String category) {
        return products.stream()
                .filter(p -> p.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    public List<Product> getByPriceRange(double min, double max) {
        return products.stream()
                .filter(p -> p.getPrice() >= min && p.getPrice() <= max)
                .collect(Collectors.toList());
    }
}