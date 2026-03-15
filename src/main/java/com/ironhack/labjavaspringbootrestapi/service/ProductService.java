package com.ironhack.labjavaspringbootrestapi.service;

import com.ironhack.labjavaspringbootrestapi.exception.InvalidPriceRangeException;
import com.ironhack.labjavaspringbootrestapi.exception.ResourceNotFoundException;
import com.ironhack.labjavaspringbootrestapi.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final List<Product> products = new ArrayList<>();

    public ProductService() {
        products.add(new Product("Laptop", "Electronics", 10, 1500.0));
    }

    public Product create(Product product) {
        products.add(product);
        return product;
    }

    public List<Product> findAll() {
        return products;
    }

    public Product findByName(String name) {
        return products.stream()
                .filter(p -> p.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with name: " + name));
    }

    public Product update(String name, Product updatedProduct) {

        Product existingProduct = findByName(name);
        existingProduct.setCategory(updatedProduct.getCategory());
        existingProduct.setPrice(updatedProduct.getPrice());
        existingProduct.setQuantity(updatedProduct.getQuantity());

        return existingProduct;
    }

    public void delete(String name) {
        Product product = findByName(name);
        products.remove(product);
    }

    public List<Product> findByCategory(String category) {

        return products.stream()
                .filter(p -> p.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());

    }

    public List<Product> findByPriceRange(double min, double max) {
        if (min > max || min < 0) {
            throw new InvalidPriceRangeException("Invalid price range provided.");
        }

        return products.stream()
                .filter(p -> p.getPrice() >= min && p.getPrice() <= max)
                .collect(Collectors.toList());
    }
}