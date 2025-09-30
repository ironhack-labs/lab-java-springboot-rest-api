package com.ironhack.week05Lab.services;

import com.ironhack.week05Lab.exceptions.ProductNameNotFoundException;
import com.ironhack.week05Lab.models.Product;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.*;

/*
Create a ProductService class that manages a List of Products and has methods to:

Add a new product
Get all products
Get product by name
Update product
Delete product
Get products by category
Get products by price range
 */
@Service
public class ProductService {
    private List<Product> products;

    @PostConstruct
    public void init() {
        products = new ArrayList<>(List.of(
                new Product("Cotton Candy", 10, "Sweets", 100),
                new Product("Noodles", 5, "Food", 10),
                new Product("Parfume", 90, "Cosmetics", 50)
        ));
    }

    public List<Product> getAllProducts() {
        return products;
    }

    public Product getProductByName(String name) {
        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(name)) {
                return product;
            }
        }

        throw new ProductNameNotFoundException(name);
    }

    public Product addProduct(Product product) {
        products.add(product);
        return product;
    }

    public Product updateProduct(String name, Product product) {
        List<Product> products = getAllProducts();

        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getName().equalsIgnoreCase(name)) {
                products.set(i, product);
                return products.get(i);
            }
        }

        throw new ProductNameNotFoundException(name);
    }

    public void deleteProduct(String name) {
        products.removeIf(product -> product.getName().equalsIgnoreCase(name));
    }

    public List<Product> getProductsByCategory(String category) {
        return products.stream().filter(product -> product.getCategory().equalsIgnoreCase(category)).toList();
    }

    public List<Product> getProductsByPriceRange(double min, double max) {
        return products.stream().filter(product -> product.getPrice() >= min && product.getPrice() <= max).toList();
    }
}
