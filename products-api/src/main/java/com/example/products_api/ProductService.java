package com.example.products_api;

import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        products.add(product);
    }

    public List<Product> getAll() {
        return products;
    }

    public Product getByName(String name) {
        return products.stream()
                .filter(p -> p.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Product not found"));
    }

    public void update(String name, Product updated) {
        Product p = getByName(name);
        p.setPrice(updated.getPrice());
        p.setQuantity(updated.getQuantity());
        p.setCategory(updated.getCategory());
    }

    public void delete(String name) {
        Product p = getByName(name);
        products.remove(p);
    }

    public List<Product> getByCategory(String category) {
        return products.stream()
                .filter(p -> p.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    public List<Product> getByPriceRange(double min, double max) {
        if (min < 0 || max < min) {
            throw new IllegalArgumentException("Invalid price range");
        }
        return products.stream()
                .filter(p -> p.getPrice() >= min && p.getPrice() <= max)
                .collect(Collectors.toList());
    }
}
