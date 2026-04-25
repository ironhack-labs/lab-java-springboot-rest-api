package com.ironhack.api.service;

import com.ironhack.api.exception.InvalidPriceRangeException;
import com.ironhack.api.exception.ResourceNotFoundException;
import com.ironhack.api.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final List<Product> products = new ArrayList<>();

    public List<Product> addProduct(Product product) {
        products.add(product);
        return products;
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(products);
    }

    public Product getProductByName(String name) {
        return products.stream()
            .filter(p -> p.getName().equalsIgnoreCase(name))
            .findFirst()
            .orElseThrow(() -> new ResourceNotFoundException(
                "Producto no encontrado: " + name));
    }

    public List<Product> updateProduct(String name, Product updatedProduct) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getName().equalsIgnoreCase(name)) {
                updatedProduct.setName(name);
                products.set(i, updatedProduct);
                return products;
            }
        }
        throw new ResourceNotFoundException(
            "Producto no encontrado: " + name);
    }

    public List<Product> deleteProduct(String name) {
        boolean removed = products.removeIf(
            p -> p.getName().equalsIgnoreCase(name));
        if (!removed) {
            throw new ResourceNotFoundException(
                "Producto no encontrado: " + name);
        }
        return products;
    }

    public List<Product> getByCategory(String category) {
        return products.stream()
            .filter(p -> p.getCategory().equalsIgnoreCase(category))
            .collect(Collectors.toList());
    }

    public List<Product> getByPriceRange(Double min, Double max) {
        if (min == null || max == null || min < 0 || max < 0) {
            throw new InvalidPriceRangeException(
                "Rango inválido: min y max deben ser >= 0");
        }
        if (min > max) {
            throw new InvalidPriceRangeException(
                "min no puede ser mayor que max");
        }
        return products.stream()
            .filter(p -> p.getPrice() >= min && p.getPrice() <= max)
            .collect(Collectors.toList());
    }
}
