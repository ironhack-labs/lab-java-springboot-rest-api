package com.workshop.week5lab.services;

import com.workshop.week5lab.InvalidPriceRangeException;
import com.workshop.week5lab.ResourceNotFoundException;
import com.workshop.week5lab.models.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service   
public class ProductService {
    private final List<Product> products = new ArrayList<>();

    public Product addProduct(Product product) {
        products.add(product);
        return product;
    }

    public List<Product> allProducts(){
       return products;
    }

    public Product productByName(String name) throws ResourceNotFoundException {
        for (Product product : products){
            if(Objects.equals(name, product.getName())){
                return product;
            }
        }
        throw new ResourceNotFoundException();
    }
    public Product updateProduct(Product updated) throws ResourceNotFoundException {
        for (Product p : products){
            if(Objects.equals(updated.getName(), p.getName())){
                p.setPrice(updated.getPrice());
                p.setQuantity(updated.getQuantity());
                p.setCategory(updated.getCategory());
                return p;
            }
        }
        throw new ResourceNotFoundException();
    }
    public void deleteProduct(String name) throws ResourceNotFoundException {
        for (Product product : products){
            if(Objects.equals(name, product.getName())){
                products.remove(product);
            }
        }
        throw new ResourceNotFoundException();
    }
    public List<Product> productsByCategory(String category) {
        List<Product> result = new ArrayList<>();
        for (Product p : products) {
            if(Objects.equals(p.getCategory(), category)) {
                result.add(p);
            }
        }
        return result;
    }
    public List<Product> productsByPriceRange(double min, double max) {
        if (min < 0 || max > 999) {
            throw new InvalidPriceRangeException();
        }
        List<Product> result = new ArrayList<>();
        for (Product p : products) {
            if(min <= p.getPrice() && p.getPrice() <= max) {
                result.add(p);
            }
        }
        return result;
    }
}
