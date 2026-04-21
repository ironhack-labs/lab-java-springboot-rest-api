package com.example.week5springboot.service;

import com.example.week5springboot.dto.ProductDTO;
import com.example.week5springboot.model.Product;
import com.example.week5springboot.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final List<Product> products = new ArrayList<>();

    public Product addProduct(ProductDTO dto) {
        Product product = new Product(dto.getName(), dto.getPrice(), dto.getCategory(), dto.getQuantity());
        products.add(product);
        return product;
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(products);
    }

    public Product getProductByName(String name) {
        return products.stream()
                .filter(p -> p.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Product not found: " + name));
    }

    public Product updateProduct(String name, ProductDTO dto) {
        Product existing = getProductByName(name);
        existing.setPrice(dto.getPrice());
        existing.setCategory(dto.getCategory());
        existing.setQuantity(dto.getQuantity());
        return existing;
    }

    public boolean deleteProduct(String name) {
        return products.removeIf(p -> p.getName().equalsIgnoreCase(name));
    }

    public List<Product> getProductsByCategory(String category) {
        return products.stream()
                .filter(p -> p.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    public List<Product> getProductsByPriceRange(double minPrice, double maxPrice) {
        return products.stream()
                .filter(p -> p.getPrice() >= minPrice && p.getPrice() <= maxPrice)
                .collect(Collectors.toList());
    }
}