package com.ironhacklab5.productapiapplication.service;

import com.ironhacklab5.productapiapplication.exception.InvalidPriceRangeException;
import com.ironhacklab5.productapiapplication.exception.ProductNotFoundException;
import com.ironhacklab5.productapiapplication.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service layer for all product operations.
 *
 * Uses an in-memory ArrayList as the data store.
 * All business logic and validation (e.g. price range checks) lives here,
 * keeping the controller thin and focused on HTTP concerns.
 */
@Service
public class ProductService {

    // In-memory store — replaced by a database in production
    private final List<Product> products = new ArrayList<>();

    /**
     * Adds a new product to the store.
     *
     * @param product the validated product to add
     * @return the saved product
     */
    public Product addProduct(Product product) {
        products.add(product);
        return product;
    }

    /**
     * Returns a copy of all products in the store.
     */
    public List<Product> getAllProducts() {
        return new ArrayList<>(products);
    }

    /**
     * Finds a product by its name (case-insensitive).
     *
     * @param name the product name to search for
     * @return the matching product
     * @throws ProductNotFoundException if no product with that name exists
     */
    public Product getProductByName(String name) {
        return products.stream()
                .filter(p -> p.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new ProductNotFoundException(name));
    }

    /**
     * Updates an existing product identified by name.
     * Replaces all fields with the values from the incoming product object.
     *
     * @param name           the name of the product to update
     * @param updatedProduct the new product data
     * @return the updated product
     * @throws ProductNotFoundException if no product with that name exists
     */
    public Product updateProduct(String name, Product updatedProduct) {
        Product existing = getProductByName(name); // throws if not found

        existing.setName(updatedProduct.getName());
        existing.setPrice(updatedProduct.getPrice());
        existing.setCategory(updatedProduct.getCategory());
        existing.setQuantity(updatedProduct.getQuantity());

        return existing;
    }

    /**
     * Deletes the product with the given name.
     *
     * @param name the name of the product to delete
     * @throws ProductNotFoundException if no product with that name exists
     */
    public void deleteProduct(String name) {
        Product product = getProductByName(name); // throws if not found
        products.remove(product);
    }

    /**
     * Returns all products belonging to the given category (case-insensitive).
     *
     * @param category the category to filter by
     * @return list of matching products (may be empty)
     */
    public List<Product> getProductsByCategory(String category) {
        return products.stream()
                .filter(p -> p.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    /**
     * Returns all products whose price falls within [min, max].
     *
     * @param min the minimum price (inclusive)
     * @param max the maximum price (inclusive)
     * @return list of matching products (may be empty)
     * @throws InvalidPriceRangeException if min is greater than max
     */
    public List<Product> getProductsByPriceRange(double min, double max) {
        if (min > max) {
            throw new InvalidPriceRangeException(min, max);
        }

        return products.stream()
                .filter(p -> p.getPrice() >= min && p.getPrice() <= max)
                .collect(Collectors.toList());
    }
}