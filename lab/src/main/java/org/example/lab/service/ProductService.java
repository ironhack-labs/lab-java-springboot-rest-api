package org.example.lab.service;

import org.example.lab.exception.InvalidPriceRangeException;
import org.example.lab.exception.ProductNotFoundException;
import org.example.lab.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    private List<Product> products = new ArrayList<>();

    public ProductService() {
        products.add(new Product("Laptop", 1200.0, "Electronics", 5));
        products.add(new Product("Phone", 800.0, "Electronics", 10));
    }

    public List<Product> getAllProducts() {
        return products;
    }

    public Product getProductByName(String name) {
        for (Product p : products) {
            if (p.getName().equalsIgnoreCase(name)) {
                return p;
            }
        }
        throw new ProductNotFoundException("Product not found with name: " + name);
    }

    public void addNewProduct(Product product) {
        products.add(product);
    }

    public void updateProduct(String name, Product updateProduct) {
        for (Product p : products) {
            if (p.getName().equalsIgnoreCase(name)) {
                p.setName(updateProduct.getName());
                p.setPrice(updateProduct.getPrice());
                p.setCategory(updateProduct.getCategory());
                p.setQuantity(updateProduct.getQuantity());
                return;
            }
        }
    }

    public void deleteProduct(String name) {
        for (Product p : products) {
            if (p.getName().equalsIgnoreCase(name)) {
                products.remove(p);
                return;
            }
        }
        throw new ProductNotFoundException("Product not found with name: " + name);
    }

    public List<Product> getProductsByCategory(String category) {

        List<Product> filteredProduct = new ArrayList<>();
        if (category != null && !category.isBlank()){
            for (Product p : products) {
                if (p.getCategory().equalsIgnoreCase(category)) {
                    filteredProduct.add(p);
                }
            }
        }else {
            return new ArrayList<>(products);
        }
        return filteredProduct;
    }

    public List<Product> getProductsByPrice(double min, double max) {
        if (min > max) {
            throw new InvalidPriceRangeException("Minimum price cannot be greater than maximum price");
        }
        List<Product> filteredProduct = new ArrayList<>();

        for (Product p : products) {
            if (p.getPrice() >= min && p.getPrice() <= max) {
                filteredProduct.add(p);
            }
        }
        return filteredProduct;
    }
}
