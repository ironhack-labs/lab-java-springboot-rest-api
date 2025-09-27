package com.example.lab.service;

import com.example.lab.exception.NotFoundException;
import com.example.lab.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final List<Product> products = new ArrayList<>();

    public Product add(Product product) {
        products.add(product);
        return product;
    }

    public List<Product> getAll() {
        return products;
    }

    public Product getByName(String name) {
        return products.stream()
                .filter(p -> p.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Product not found: " + name));
    }

    public Product update(String name, Product updated) {
        Product existing = getByName(name);
        existing.setCategory(updated.getCategory());
        existing.setQuantity(updated.getQuantity());
        existing.setPrice(updated.getPrice());
        return existing;
    }

    public void delete(String name) {
        boolean removed = products.removeIf(p -> p.getName().equalsIgnoreCase(name));
        if (!removed) throw new NotFoundException("Product not found: " + name);
    }

    public List<Product> byCategory(String category) {
        return products.stream()
                .filter(p -> p.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    public List<Product> byPriceRange(double min, double max) {
        return products.stream()
                .filter(p -> p.getPrice() >= min && p.getPrice() <= max)
                .collect(Collectors.toList());
    }
}
