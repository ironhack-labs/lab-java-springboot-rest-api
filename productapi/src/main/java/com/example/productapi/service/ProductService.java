package com.example.productapi.service;

import com.example.productapi.exception.InvalidPriceRangeException;
import com.example.productapi.exception.ProductNotFoundException;
import com.example.productapi.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public Product getProductByName(String name) {
        for (Product p : products) {
            if (p.getName().equalsIgnoreCase(name)) {
                return p;
            }
        }
        throw new ProductNotFoundException("Product not found");
    }

    public Product updateProduct(String name, Product updatedProduct) {
        Product product = getProductByName(name);

        product.setName(updatedProduct.getName());
        product.setPrice(updatedProduct.getPrice());
        product.setCategory(updatedProduct.getCategory());
        product.setQuantity(updatedProduct.getQuantity());

        return product;
    }

    public void deleteProduct(String name) {
        Product product = getProductByName(name);
        products.remove(product);
    }

    public List<Product> getProductsByCategory(String category) {
        List<Product> result = new ArrayList<>();

        for (Product p : products) {
            if (p.getCategory().equalsIgnoreCase(category)) {
                result.add(p);
            }
        }

        return result;
    }

    public List<Product> getProductsByPriceRange(double min, double max) {

        if (min > max) {
            throw new InvalidPriceRangeException("Min price cannot be greater than max price");
        }

        List<Product> result = new ArrayList<>();

        for (Product p : products) {
            if (p.getPrice() >= min && p.getPrice() <= max) {
                result.add(p);
            }
        }

        return result;
    }
}
