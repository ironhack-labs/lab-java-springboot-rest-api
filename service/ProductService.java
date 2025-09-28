package com.ironhack.labweek5.service;

import com.ironhack.labweek5.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//Create a ProductService class that manages a List of Products and has methods to:
//Get all products
//Add a new product
//Update product
//Delete product
//Get product by name
//Get products by category
//Get products by price range

@Service
public class ProductService{
    private final ArrayList<Product> products = new ArrayList<>();

    public ProductService(){
        products.add(new Product("Labubu", 49.99, "keychain", 17));
        products.add(new Product("Vaseline Rosy Lips", 2.50, "cosmetic", 247));
        products.add(new Product("Lafufu", 4.49, "keychain", 321));
    }

    //get all
    public ArrayList<Product> getAllProducts(){
        return products;
    }

    //get product by name
    public Product getProductByName(String name){
        return products.stream()
                .filter(p -> p.getName().equals(name))
                .findFirst()
                .orElseThrow(()-> new RuntimeException("No product with that name"));
    }

    //get products by category
    public List<Product> getProductsByCategory(String category){
        List<Product> productsByCategory = products.stream()
                .filter(p -> p.getCategory().equals(category))
                .collect(Collectors.toList());

        if(productsByCategory.isEmpty()){
            throw new RuntimeException("No such category");
        }

        return productsByCategory;
    }

    //get products by price range
    public List<Product> getProductsByPriceRange(double min, double max){
        List<Product> productsByPriceRange = products.stream()
                .filter(p -> p.getPrice() >= min && p.getPrice() <= max)
                .collect(Collectors.toList());

        if(productsByPriceRange.isEmpty()){
            throw new RuntimeException("No products in this price range");
        }

        return productsByPriceRange;
    }


    //add a new product
    public Product create(Product product){
        products.add(product);
        return product;
    }

    //update an existing product
    public Product update(String name, Product product){
//        Product productToUpdate = products.stream()
//                .filter(p -> p.getName().equals(name))
//                .findFirst()
//                .orElseThrow(() -> new RuntimeException("No product with that name"));

        Product productToUpdate = getProductByName(name);

        productToUpdate.setName(product.getName());
        productToUpdate.setPrice(product.getPrice());
        productToUpdate.setCategory(product.getCategory());
        productToUpdate.setQuantity(product.getQuantity());

        return productToUpdate;
    }

    //delete existing product
    public void delete(String name){
        Product productToDelete = getProductByName(name);
        products.remove(productToDelete);
    }
}