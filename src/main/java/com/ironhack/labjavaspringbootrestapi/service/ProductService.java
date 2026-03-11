package com.ironhack.labjavaspringbootrestapi.service;

import com.ironhack.labjavaspringbootrestapi.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {
    private final Map<Long, Product> products = new HashMap<>();
    private Long nextId = 1L;

    public ProductService() {
        Product product = new Product(nextId,"Salam", "Sagol", 23, 22.3);
        products.put(product.getId(), product);
    }

    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    public Product findById(Long id) {
        return products.get(id);
    }

    public List<Product> findByName(String name) {
        List<Product> filtered = new ArrayList<>();
        String lowerName = name.toLowerCase();
        for (Product c: products.values()) {
            if (c.getName().toLowerCase().contains(lowerName)) {
                filtered.add(c);
            }
        }
        return filtered;
    }

    public Product create(String name, String category, int quantity, double price) {
        Product product = new Product(nextId++, name, category, quantity, price);
        products.put(product.getId(), product);
        return product;
    }

    public Product fullUpdate(Long id, String name, String category, int quantity, double price) {
        Product product = findById(id);

        product.setName(name);
        product.setCategory(category);
        product.setQuantity(quantity);
        product.setPrice(price);

        return product;
    }

    public Product partialUpdate(Long id, String name, String category, Integer quantity, Double price) {
        Product existingProduct = findById(id);

        if (name != null) {
            existingProduct.setName(name);
        }
        if (category != null) {
            existingProduct.setCategory(category);
        }
        if (quantity != null) {
            existingProduct.setQuantity(quantity);
        }
        if (price != null) {
            existingProduct.setPrice(price);
        }

        return existingProduct;
    }

    public void delete(Long id) {

        findById(id);
        products.remove(id);
    }
}
