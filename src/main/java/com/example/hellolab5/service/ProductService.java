package com.example.hellolab5.service;

import com.example.hellolab5.Product;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final List<Product> products = new ArrayList<>();

    public void add(Product p) { products.add(p); }

    public List<Product> getAll() { return products; }

    public Optional<Product> getByName(String name) {
        return products.stream().filter(p -> p.getName().equalsIgnoreCase(name)).findFirst();
    }

    public void update(String name, Product updated) {
        getByName(name).ifPresent(p -> {
            p.setPrice(updated.getPrice());
            p.setCategory(updated.getCategory());
            p.setQuantity(updated.getQuantity());
        });
    }

    public void delete(String name) {
        products.removeIf(p -> p.getName().equalsIgnoreCase(name));
    }

    public List<Product> getByCategory(String category) {
        return products.stream().filter(p -> p.getCategory().equalsIgnoreCase(category)).collect(Collectors.toList());
    }

    public List<Product> getByPriceRange(double min, double max) {
        if (min > max) throw new IllegalArgumentException("Min range cannot be greater than max");
        return products.stream().filter(p -> p.getPrice() >= min && p.getPrice() <= max).collect(Collectors.toList());
    }
}