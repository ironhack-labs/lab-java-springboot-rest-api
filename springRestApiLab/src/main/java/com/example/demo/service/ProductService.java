package com.example.demo.service;

import com.example.demo.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Service class for managing Product entities.
 * This class provides methods to perform CRUD operations and filtering on products using an in-memory map.
 */
@Service
public class ProductService {

    // In-memory storage using a map with product name as the key for quick lookups.
    private final Map<String, Product> productMap = new HashMap<>();

    /**
     * Adds a new product to the storage.
     *
     * @param product The product to add.
     */
    public void addProduct(Product product) {
        productMap.put(product.getName(), product);
    }

    /**
     * Retrieves all products from the storage.
     *
     * @return A list of all products.
     */
    public List<Product> getAllProducts() {
        return new ArrayList<>(productMap.values());
    }

    /**
     * Retrieves a product by its name.
     *
     * @param name The name of the product to find.
     * @return The product if found, otherwise null.
     */
    public Product getProductByName(String name) {
        return productMap.get(name);
    }

    /**
     * Updates an existing product.
     * If the product's name has changed, updates the key in the map accordingly.
     *
     * @param name           The original name of the product to update.
     * @param updatedProduct The updated product details.
     * @return true if the product was updated, false if not found.
     */
    public boolean updateProduct(String name, Product updatedProduct) {
        if (productMap.containsKey(name)) {
            productMap.remove(name);
            productMap.put(updatedProduct.getName(), updatedProduct);
            return true;
        }
        return false;
    }

    /**
     * Deletes a product by its name.
     *
     * @param name The name of the product to delete.
     * @return true if the product was deleted, false if not found.
     */
    public boolean deleteProduct(String name) {
        return productMap.remove(name) != null;
    }

    /**
     * Retrieves products by category (case-insensitive).
     *
     * @param category The category to filter by.
     * @return A list of products in the specified category.
     */
    public List<Product> getProductsByCategory(String category) {
        return productMap.values().stream()
                .filter(p -> p.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    /**
     * Retrieves products within a specified price range.
     *
     * @param min The minimum price (inclusive).
     * @param max The maximum price (inclusive).
     * @return A list of products within the price range.
     */
    public List<Product> getProductsByPriceRange(double min, double max) {
        return productMap.values().stream()
                .filter(p -> p.getPrice() >= min && p.getPrice() <= max)
                .collect(Collectors.toList());
    }
}