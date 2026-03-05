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
        return products.values().stream()
                .filter(c -> c.getName().toLowerCase().contains(name.toLowerCase()))
                .toList();
    }

    public Product create(String name, String category, int quantity, double price) {
        Product newProduct = new Product(nextId++, name, category, quantity, price);
        products.put(newProduct.getId(), newProduct);
        return newProduct;
    }
}
