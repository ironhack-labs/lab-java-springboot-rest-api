package com.week5.SpringBoot.REST.API.Service;

import com.week5.SpringBoot.REST.API.Model.Product;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final List<Product> products = new ArrayList<>();

    public ProductService() {
        products.add(new Product("MacBook Pro 14", 1999.99, "Electronics", 10));
        products.add(new Product("Sony WH-1000XM5", 349.00, "Electronics", 25));
        products.add(new Product("Coffee Maker", 89.50, "Home Appliances", 15));
        products.add(new Product("Gaming Chair", 250.00, "Furniture", 5));
        products.add(new Product("Running Shoes", 120.00, "Sportswear", 40));
    }

    // 1. Get all products
    public List<Product> getProductList() {
        return products;
    }

    // 2. Find product by name (Nutzt Optional für .orElseThrow im Controller)
    public Optional<Product> getProductByName(String name) {
        for (Product p : products) {
            if (p.getName().equalsIgnoreCase(name)) {
                return Optional.of(p);
            }
        }
        return Optional.empty();
    }

    // 3. Save a new product
    public Product save(@Valid Product product) {
        products.add(product);
        return product;
    }

    // 4. Update product name
    public Product updateProductName(String oldName, String newName) {
        for (Product p : products) {
            if (p.getName().equalsIgnoreCase(oldName)) {
                p.setName(newName);
                return p;
            }
        }
        return null;
    }


    public Product updateProduct(String name, Product updatedProduct) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getName().equalsIgnoreCase(name)) {
                products.set(i, updatedProduct);
                return updatedProduct;
            }
        }
        return null;
    }

    // 5. Delete product
    public Optional<Product> deleteProduct(String name) {
        for (Product p : products) {
            if (p.getName().equalsIgnoreCase(name)) {
                products.remove(p);
                return Optional.of(p);
            }
        }
        return Optional.empty();
    }

    // 6. Get by category
    public List<Product> getProductByCategory(String category) {
        List<Product> result = new ArrayList<>();
        for (Product p : products) {
            if (p.getCategory().equalsIgnoreCase(category)) {
                result.add(p);
            }
        }
        return result;
    }

    // 7. Get by price range
    public List<Product> getProductsByPriceRange(double min, double max) {
        List<Product> result = new ArrayList<>();
        for (Product p : products) {
            if (p.getPrice() >= min && p.getPrice() <= max) {
                result.add(p);
            }
        }
        return result;
    }

    // Diese Methoden sind nun Duplikate oder Aliasse für die obigen:
    public List<Product> findAll() {
        return getProductList();
    }

    public Product findByName(String name) {
        return getProductByName(name).orElse(null);
    }
}



