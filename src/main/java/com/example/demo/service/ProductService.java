package com.example.demo.service;

import com.example.demo.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {

    private final Map<Long, Product> products = new HashMap<>();
    private Long nextId = 1L;

    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    public Product findById(Long id) {
        return products.get(id);
    }

    public List<Product> findByName(String name) {
        return products.values().stream()
                .filter(p -> p.getName().toLowerCase().contains(name.toLowerCase()))
                .toList();
    }

    public List<Product> findByCategory(String category){
        return products.values().stream().filter( p -> p.getCategory().toLowerCase().contains(category.toLowerCase()))
                .toList();
    }

    public List<Product> findByPriceRange(double min, double max){
        return products.values()
                .stream()
                .filter(p -> p.getPrice() >= min && p.getPrice() <= max)
                .toList();
    }

    public Product create(String name,double price,String category,int quantity) {
        Product product = new Product(nextId++, name, price, category,quantity);

        products.put(product.getId(), product);
        return product;
    }
}
