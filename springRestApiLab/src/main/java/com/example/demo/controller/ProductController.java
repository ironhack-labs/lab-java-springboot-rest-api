package com.example.demo.controller;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for managing Product resources.
 * All endpoints require a valid API-Key header for authentication.
 */
@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private static final String AUTH_KEY = "123456";

    /**
     * Constructor for injecting the ProductService dependency.
     *
     * @param productService The service for product operations.
     */
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Checks if the provided API key is unauthorized (null or incorrect).
     *
     * @param apiKey The API key from the request header.
     * @return true if unauthorized, false otherwise.
     */
    private boolean isUnauthorized(String apiKey) {
        return apiKey == null || !apiKey.equals(AUTH_KEY);
    }

    /**
     * Adds a new product.
     *
     * @param apiKey  The API key header.
     * @param product The product to add (validated).
     * @return Response indicating success or unauthorized.
     */
    @PostMapping
    public ResponseEntity<String> addProduct(@RequestHeader(value = "API-Key", required = false) String apiKey,
                                             @Valid @RequestBody Product product) {
        if (isUnauthorized(apiKey)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or missing API key");
        }
        productService.addProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body("Product added successfully!");
    }

    /**
     * Retrieves all products.
     *
     * @param apiKey The API key header.
     * @return List of products or unauthorized response.
     */
    @GetMapping
    public ResponseEntity<?> getAllProducts(@RequestHeader(value = "API-Key", required = false) String apiKey) {
        if (isUnauthorized(apiKey)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or missing API key");
        }
        return ResponseEntity.ok(productService.getAllProducts());
    }

    /**
     * Retrieves a product by name.
     *
     * @param apiKey The API key header.
     * @param name   The name of the product.
     * @return The product or not found/unauthorized response.
     */
    @GetMapping("/{name}")
    public ResponseEntity<?> getProductByName(@RequestHeader(value = "API-Key", required = false) String apiKey,
                                              @PathVariable String name) {
        if (isUnauthorized(apiKey)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or missing API key");
        }
        Product product = productService.getProductByName(name);
        if (product == null) {
            throw new ResourceNotFoundException("Product with name '" + name + "' not found");
        }
        return ResponseEntity.ok(product);
    }

    /**
     * Updates a product by name.
     *
     * @param apiKey         The API key header.
     * @param name           The original name of the product.
     * @param updatedProduct The updated product details (validated).
     * @return Success message or not found/unauthorized response.
     */
    @PutMapping("/{name}")
    public ResponseEntity<String> updateProduct(@RequestHeader(value = "API-Key", required = false) String apiKey,
                                                @PathVariable String name,
                                                @Valid @RequestBody Product updatedProduct) throws Throwable {
        if (isUnauthorized(apiKey)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or missing API key");
        }
        boolean updated = productService.updateProduct(name, updatedProduct);
        if (!updated) {
            throw new Throwable("Product with name '" + name + "' not found");
        }
        return ResponseEntity.ok("Product successfully updated!");
    }

    /**
     * Deletes a product by name.
     *
     * @param apiKey The API key header.
     * @param name   The name of the product to delete.
     * @return Success message or not found/unauthorized response.
     */
    @DeleteMapping("/{name}")
    public ResponseEntity<String> deleteProduct(@RequestHeader(value = "API-Key", required = false) String apiKey,
                                                @PathVariable String name) {
        if (isUnauthorized(apiKey)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or missing API key");
        }
        boolean deleted = productService.deleteProduct(name);
        if (!deleted) {
            throw new com.example.demo.exception.ResourceNotFoundException("Product with name '" + name + "' not found");
        }
        return ResponseEntity.ok("Product successfully deleted!");
    }

    /**
     * Retrieves products by category.
     *
     * @param apiKey   The API key header.
     * @param category The category to filter by.
     * @return List of products in the category or unauthorized response.
     */
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Product>> getProductsByCategory(@RequestHeader(value = "API-Key", required = false) String apiKey,
                                                               @PathVariable String category) {
        if (isUnauthorized(apiKey)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        return ResponseEntity.ok(productService.getProductsByCategory(category));
    }

    /**
     * Retrieves products within a price range.
     *
     * @param apiKey The API key header.
     * @param min    The minimum price.
     * @param max    The maximum price.
     * @return List of products in the range or error responses.
     */
    @GetMapping("/price")
    public ResponseEntity<List<Product>> getProductsByPriceRange(@RequestHeader(value = "API-Key", required = false) String apiKey,
                                                                 @RequestParam double min,
                                                                 @RequestParam double max) {
        if (isUnauthorized(apiKey)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        if (min > max) {
            throw new IllegalArgumentException("Invalid price range: min cannot be greater than max");
        }
        return ResponseEntity.ok(productService.getProductsByPriceRange(min, max));
    }
}