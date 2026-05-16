package com.ironhack.lab.service;

import com.ironhack.lab.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final List<Product> products = new ArrayList<>();

    public Product addProduct(Product product) {
        products.add(product);
        return product;
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(products);
    }

    public Optional<Product> getProductByName(String name) {
        return products.stream()
                .filter(p -> p.getName().equalsIgnoreCase(name))
                .findFirst();
    }

    public Optional<Product> updateProduct(String name, Product updatedProduct) {
        Optional<Product> existingProduct = getProductByName(name);
        if (existingProduct.isPresent()) {
            Product product = existingProduct.get();
            product.setName(updatedProduct.getName());
            product.setPrice(updatedProduct.getPrice());
            product.setCategory(updatedProduct.getCategory());
            product.setQuantity(updatedProduct.getQuantity());
            return Optional.of(product);
        }
        return Optional.empty();
    }

    public boolean deleteProduct(String name) {
        return products.removeIf(p -> p.getName().equalsIgnoreCase(name));
    }

    public List<Product> getProductsByCategory(String category) {
        return products.stream()
                .filter(p -> p.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    public List<Product> getProductsByPriceRange(Double min, Double max) {
        return products.stream()
                .filter(p -> p.getPrice() >= min && p.getPrice() <= max)
                .collect(Collectors.toList());
    }
}

