package com.crud.crudlab5.service;

import com.crud.crudlab5.exception.InvalidPriceRangeException;
import com.crud.crudlab5.exception.ProductNotFoundException;
import com.crud.crudlab5.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final List<Product> products = new ArrayList<>();

    public Product addProduct(Product product){
        products.add(product);
        return product;
    }

    public List<Product> getAllProducts(){
        return products;
    }
    public Product getProductByName(String name){
        return products.stream()
                .filter(p -> p.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new ProductNotFoundException("Product not found " +name));

    }
    public Product updateProduct(String name, Product updatedProduct){
        Product existing = getProductByName(name);
        existing.setPrice(updatedProduct.getPrice());
        existing.setCategory(updatedProduct.getCategory());
        existing.setQuantity(updatedProduct.getQuantity());
        return existing;
    }
    public void deleteProduct(String name){
        Product product = getProductByName(name);;
        products.remove(product);
    }

    public List<Product> getProductsByCategory(String category){
        return products.stream()
                .filter(p -> p.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }
    public List<Product> getProductsByPriceRange(double min, double max){
        if(min > max) throw new InvalidPriceRangeException("Min cannot be greater than Max");
        return products.stream()
                .filter(p -> p.getPrice() >= min && p.getPrice() <= max)
                .collect(Collectors.toList());
    }

}
