package com.ironhack.lab_java_springboot_rest_api.service;

import com.ironhack.lab_java_springboot_rest_api.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private List<Product> products = new ArrayList<>();

    public Product addProduct(Product product) {
        products.add(product);
        return product;
    }

    public List<Product> getAllProducts() {
        return products;
    }

    public Product getProductByName(String name) {
        for (Product product : products) {
             if (product.getName().equalsIgnoreCase(name)) {
                return product;
            }
        }
        return null;
    }

    public Product updateProduct(String name, Product updateProduct) {
        for (Product product: products) {
            if (product.getName().equalsIgnoreCase(name)) {
                product.setPrice(updateProduct.getPrice());
                product.setCategory(updateProduct.getCategory());
                product.setQuantity(updateProduct.getQuantity());
                return product;
            }
        }
        return null;
    }

    public boolean deleteProduct(String name) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getName().equalsIgnoreCase(name)) {
                products.remove(i);
                return true;
            }
        }
        return false;
    }

    public List<Product> getProductsByCategory(String category) {
        List<Product> result = new ArrayList<>();
        for (Product product : products) {
            if (product.getCategory().equalsIgnoreCase(category)) {
                result.add(product);
            }
        }
        return result;
    }

    public List<Product> getProductsByPriceRange(double min, double max) {
        List<Product> result = new ArrayList<>();
        for (Product product : products) {
            if (product.getPrice() >= min && product.getPrice() <= max) {
                result.add(product);
            }
        }
        return result;
    }
}

