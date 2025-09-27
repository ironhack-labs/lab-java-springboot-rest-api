package com.ironhack.restapi.controller;
import com.ironhack.restapi.model.Product;
import com.ironhack.restapi.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private static final String API_KEY = "123456";

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Helper method to validate API key
    private boolean isValidApiKey(String apiKey) {
        return !API_KEY.equals(apiKey);
    }

    @PostMapping
    public ResponseEntity<String> addProduct(
            @RequestHeader("API-Key") String apiKey,
            @Valid @RequestBody Product product) {
        if (isValidApiKey(apiKey)) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        productService.addProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body("Product added successfully");
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(@RequestHeader("API-Key") String apiKey) {
        if (isValidApiKey(apiKey)) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{name}")
    public ResponseEntity<Product> getProductByName(
            @RequestHeader("API-Key") String apiKey,
            @PathVariable String name) {
        if (isValidApiKey(apiKey)) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        return productService.getProductByName(name)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{name}")
    public ResponseEntity<String> updateProduct(
            @RequestHeader("API-Key") String apiKey,
            @PathVariable String name,
            @Valid @RequestBody Product product) {
        if (isValidApiKey(apiKey)) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        boolean updated = productService.updateProduct(name, product);
        return updated ? ResponseEntity.ok("Product updated successfully")
                : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<String> deleteProduct(
            @RequestHeader("API-Key") String apiKey,
            @PathVariable String name) {
        if (isValidApiKey(apiKey)) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        boolean deleted = productService.deleteProduct(name);
        return deleted ? ResponseEntity.ok("Product deleted successfully")
                : ResponseEntity.notFound().build();
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Product>> getProductsByCategory(
            @RequestHeader("API-Key") String apiKey,
            @PathVariable String category) {
        if (isValidApiKey(apiKey)) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        return ResponseEntity.ok(productService.getProductsByCategory(category));
    }

    @GetMapping("/price")
    public ResponseEntity<List<Product>> getProductsByPriceRange(
            @RequestHeader("API-Key") String apiKey,
            @RequestParam double min,
            @RequestParam double max) {
        if (isValidApiKey(apiKey)) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        return ResponseEntity.ok(productService.getProductsByPriceRange(min, max));
    }
}
