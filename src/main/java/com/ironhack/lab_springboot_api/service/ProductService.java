package com.ironhack.lab_springboot_api.service;

import com.ironhack.lab_springboot_api.model.Product;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private List<Product> productList = new ArrayList<>();

    public Product addProduct(Product product) {
        productList.add(product);
        return product;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public Product getProductByName(String name) {
        for (Product p : productList) {
            if (p.getName().equalsIgnoreCase(name)) {
                return p;
            }
        }
        return null;
    }

    public List<Product> getByCategory(String category) {
        List<Product> result = new ArrayList<>();
        for (Product p : productList) {
            if (p.getCategory().equalsIgnoreCase(category)) {
                result.add(p);
            }
        }
        return result;
    }

    public List<Product> getByPriceRange(double min, double max) {
        List<Product> result = new ArrayList<>();
        for (Product p : productList) {
            if (p.getPrice() >= min && p.getPrice() <= max) {
                result.add(p);
            }
        }
        return result;
    }

    public Product updateProduct(String name, Product updatedProduct) {
        for (Product p : productList) {
            if (p.getName().equalsIgnoreCase(name)) {
                p.setPrice(updatedProduct.getPrice());
                p.setCategory(updatedProduct.getCategory());
                p.setQuantity(updatedProduct.getQuantity());
                return p;
            }
        }
        return null;
    }

    public void deleteProduct(String name) {
        productList.removeIf(p -> p.getName().equalsIgnoreCase(name));
    }
}