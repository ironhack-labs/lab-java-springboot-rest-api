package com.example.demo.service;

import com.example.demo.model.Product;
import jdk.jfr.Category;
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

    public Product fullUpdate(String name,Product product1){
        Product product= (Product) findByName(name);
        product.setName(product1.getName());
        product.setCategory(product1.getCategory());
        product.setQuantity(product1.getQuantity());
        product.setPrice(product1.getPrice());
        return product;
    }
    public Product create(String name,double price,String category,int quantity) {
        Product product = new Product(nextId++, name, price, category,quantity);

        products.put(product.getId(), product);
        return product;
    }

    public Product partialUpdate(String name,Product product){
        Product product1= (Product) findByName(name);
        String name1= product.getName();
        double price1= product.getPrice();
        String category1= product.getCategory();
        int quantity1= product.getQuantity();

        if( name1!= null){product1.setName(name);}
        if(quantity1 >=0){product1.setQuantity(quantity1);}
        if( category1 != null){product1.setCategory(category1);}
        if( price1 >=0){product1.setPrice(price1);}
        return product1;
    }




    public Product delete(String name){
        findByName(name);
        products.remove(name);
        return null;
    }


}
