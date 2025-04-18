package com.ironhack.springbootrestapi.service;

import com.ironhack.springbootrestapi.exception.ProductNotFound;
import com.ironhack.springbootrestapi.model.Product;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    List<Product> products;

    @PostConstruct
    public void init() {
        products = new ArrayList<>(
                List.of(
                        new Product("Laptop", new BigDecimal("800.00"), "hardware", 10),
                        new Product("Smartphone", new BigDecimal("500.00"), "hardware", 20),
                        new Product("Headphones", new BigDecimal("50.00"), "hardware", 100),
                        new Product("Monitor", new BigDecimal("200.00"), "hardware", 15),
                        new Product("Apple", new BigDecimal("1.00"), "food", 10),
                        new Product("Banana", new BigDecimal("3.00"), "food", 20),
                        new Product("Orange", new BigDecimal("2.00"), "food", 30),
                        new Product("Spatula", new BigDecimal("10.00"), "kitchen", 10),
                        new Product("Knife", new BigDecimal("5.00"), "kitchen", 5),
                        new Product("Fork", new BigDecimal("2.00"), "kitchen", 1)
                )
        );
    }

    public List<Product> getAllProducts() {
        if(products == null || products.isEmpty()) {
            throw new ProductNotFound("No products found");
        }
        return products;
    }

    public Product getProductByName(String name) {
        for(Product product : products) {
            if(product.getName().equalsIgnoreCase(name)) {
                return product;
            }
        }
        throw new ProductNotFound("Product not found");
    }

    public List<Product> getProductsByCategory(String category) {
        List<Product> productsByCategory = new ArrayList<>();
        for(Product product : products) {
            if(product.getCategory().equalsIgnoreCase(category)) {
                productsByCategory.add(product);
            }
        }
        if(productsByCategory == null || productsByCategory.isEmpty()) {
            throw new ProductNotFound("No products found in this category");
        }

        return productsByCategory;
    }

    public List<Product> getProductsByPriceRange(BigDecimal min, BigDecimal max) {
        List<Product> productsByPriceRange = new ArrayList<>();
        for(Product product : products) {
            if(product.getPrice().compareTo(min) >= 0 && product.getPrice().compareTo(max) <= 0) {
                productsByPriceRange.add(product);
            }
        }

        if(productsByPriceRange.isEmpty()) {
            throw new ProductNotFound("No products found in this price range");
        }

        return productsByPriceRange;
    }

    public Product addProduct(Product product) {
        products.add(product);
        return product;
    }

    public Product updateProduct(String name, Product product) {
        for(int i = 0; i < products.size(); i++) {
            if(products.get(i).getName().equalsIgnoreCase(name)) {
                products.set(i, product);
                return product;
            }
        }

        throw new ProductNotFound("Product not found");
    }

    public Product deleteProduct(String name) {
        for(int i = 0; i < products.size(); i++) {
            if(products.get(i).getName().equalsIgnoreCase(name)) {
                Product product = products.get(i);
                products.remove(i);
                return product;
            }
        }

        throw new ProductNotFound("Product not found");
    }

}
