package com.ironhack.lab3.service;


import com.ironhack.lab3.model.Product;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {
    private final Map<Long, Product> products = new HashMap<>();

    //Get all products
    public List <Product>findAll() {
        return new ArrayList<>(products.values());
    }

    //Get product by name
    public List <Product> findByName( String name){
        return products.values().stream().filter(n -> n.getName().toLowerCase().contains(name.toLowerCase())).toList();
    }

    //Get products by price range
    public List<Product> findByPriceRange(double minPrice, double maxPrice) {
        return products.values().stream().filter(
                p -> p.getPrice() >= minPrice && p.getPrice() <= maxPrice)
                .toList();
    }

    //Get products by category
    public  List<Product> findByCategory (String category) {
        return products.values().stream().filter(
                c -> c.getCategory().toLowerCase().contains(category.toLowerCase())).toList();
    }

    //Add a new product
    public  Product create (Product product){
        Long id =(long) products.size()+1;
        products.put(id ,product);
        return product;
    }

    //Update product

    public Product update (Long id , @Valid Product product){
        Product existing = products.get(id);
        product.setName(product.getName());
        product.setPrice(product.getPrice());
        product.setCategory(product.getCategory());
        product.setQuantity(product.getQuantity());

        return existing;
    }

    //Delete product

    public void remove (Long id ){
        if (!products.containsKey(id)){
            throw new RuntimeException("Product not found");
        }
        products.remove(id);
    }
}

