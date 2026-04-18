package com.workshop.springbootrestapi.service;

import com.workshop.springbootrestapi.exception.ProductNotFoundException;
import com.workshop.springbootrestapi.model.Product;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final List<Product> products = new ArrayList<>(List.of(
            new Product("iPhone 15", new BigDecimal("999.99"), "Mobile", 10),
            new Product("Samsung S24", new BigDecimal("899.99"), "Mobile", 15),
            new Product("MacBook Pro", new BigDecimal("1999.99"), "Laptop", 5),
            new Product("iPad Air", new BigDecimal("649.99"), "Tablet", 8),
            new Product("Sony Headphones", new BigDecimal("399.99"), "Audio", 20)
    ));

    public Product addProduct(Product product) {
        products.add(product);
        return product;
    }

    public List<Product> getAllProducts() {
        return products;
    }

    public Product getProductByName(String name) {
        return products.stream()
                .filter(p -> p.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() ->
                        new ProductNotFoundException("Product not found: " + name));
    }

    public Product updateProduct(String name, Product updatedProduct) {
        Product product = getProductByName(name);

        product.setName(updatedProduct.getName());
        product.setPrice(updatedProduct.getPrice());
        product.setCategory(updatedProduct.getCategory());
        product.setQuantity(updatedProduct.getQuantity());

        return product;
    }

    public void deleteProduct(String name) {
        Product product = getProductByName(name);
        products.remove(product);
    }

    public List<Product> getProductsByCategory(String category) {
        return products.stream()
                .filter(p -> p.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    public List<Product> getProductsByPriceRange(double min, double max) {

        if (min > max) {
            throw new IllegalArgumentException("Min price cannot be greater than max price");
        }

        return products.stream()
                .filter(p -> p.getPrice().compareTo(BigDecimal.valueOf(min)) >= 0
                        && p.getPrice().compareTo(BigDecimal.valueOf(max)) <= 0)
                .collect(Collectors.toList());
    }
}