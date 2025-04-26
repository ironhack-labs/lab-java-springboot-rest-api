package com.example.labweek5.service;

import com.example.labweek5.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
    public Product getProductByName(String name) {
        return products.stream().filter(product -> product.getName().equals(name)).findFirst().orElse(null);
    }
    public void uptadeProduct(String name, Product product) {
        product.setName(name);
        products.add(product);
    }
    public void deleteProduct(String name) {
        products.removeIf(product -> product.getName().equals(name));
    }
    public List<Product> getProductByCategory(String category) {
        return products.stream().filter(product -> product.getCategory().equals(category)).collect(Collectors.toList());
    }
    public List<Product> getProductByPriceRange(double min, double max) {
        return products.stream().filter(product -> product.getPrice() >= min && product.getPrice() <= max).collect(Collectors.toList());
    }
}
