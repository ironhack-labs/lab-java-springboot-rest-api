package com.example.LAB.SpringBoot.REST.API.Views;

import com.example.LAB.SpringBoot.REST.API.Models.Product;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductService {
//    Add a new product
//    Get all products
//    Get product by name
//    Update product
//    Delete product
//    Get products by category
//    Get products by price range
    private final Map<String, Product> products = new HashMap<>();

    public Product addProduct(Product product) {
        products.put(product.getName(), product);
        return product;
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(products.values());
    }

    public List<Product> getProductList() {
        return new ArrayList<>(products.values());
    }

    public Product getProductByName(String productName) {
        return products.get(productName);
    }

    public Product updateProduct(String name, Product updated) {
        if (products.containsKey(name)) {
            products.put(name, updated);
            return updated;
        }
        return null;
    }

    public boolean deleteProduct(String name) {
        return products.remove(name) != null;
    }

    public List<Product> getByCategory(String category) {
        return products.values().stream()
                .filter(p -> p.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    public List<Product> getByPriceRange(double min, double max) {
        return products.values().stream()
                .filter(p -> p.getPrice() >= min && p.getPrice() <= max)
                .collect(Collectors.toList());
    }

}
