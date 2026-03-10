package com.ironhack.services;

import com.ironhack.models.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final List<Product> products = new ArrayList<>();

    public Product addProduct(Product product) {
        products.add(product);
        return product;
    }

    public List<Product> getAllProducts(){
        return products;
    }

    public Optional<Product>getProductByName (String name){
        return products.stream()
                .filter(p -> p.getName().equalsIgnoreCase(name))
                .findFirst();
    }
    public Product updateProduct(String name, Product updatedProduct) {
        Optional<Product> existing = getProductByName(name);

        if (existing.isPresent()) {
            Product p = existing.get();
            p.setPrice(updatedProduct.getPrice());
            p.setCategory(updatedProduct.getCategory());
            p.setQuantity(updatedProduct.getQuantity());
            return p;
        }
        return null;
    }

    public boolean deleteProduct(String name) {
        return products.removeIf(p -> p.getName().equalsIgnoreCase(name));
    }

    public List<Product> getProductsByCategory(String category) {
        return products.stream()
                .filter(p -> p.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    public List<Product> getProductsByPriceRange(double min, double max) {
        return products.stream()
                .filter(p -> p.getPrice() >= min && p.getPrice() <= max)
                .collect(Collectors.toList());
    }
}
