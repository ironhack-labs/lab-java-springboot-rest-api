package com.fairtok.online_shopping.service;

import com.fairtok.online_shopping.classes.Product;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {
    private Map<String, Product> products = new HashMap<>();
    public Product create(String name, double price, String category, int quantity){
        if(products.containsKey(name)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product " + name + " is exists!");
        }
        Product created = new Product(name, price, category, quantity);
        products.put(name, created);
        return created;
    }
    public ArrayList<Product> listProducts(){
        return new ArrayList<>(products.values());
    }

    public Product findProductByName(String name){
        return products.get(name);
    }

    public Product update(String name, double price, String category, int quantity){
        Product existing = findProductByName(name);
        if (existing == null){
            return null;
        }
        Product replaced = new Product(name, price, category, quantity);
        products.replace(name, replaced);
        return replaced;
    }
    public void delete(String name){
        products.remove(name);
    }

    public List<Product> findByCategory(String category){
        return products.values().stream().filter(product -> product.getCategory().equalsIgnoreCase(category)).toList();
    }

    public List<Product> findByPriceRange(double priceStarts, double priceEnds){
        List<Product> result = new ArrayList<>();
        for (Product product : products.values()){
            if(product.getPrice()>=priceStarts && priceEnds>=product.getPrice()){
                result.add(product);
            }
        }
        return result;
    }
}
