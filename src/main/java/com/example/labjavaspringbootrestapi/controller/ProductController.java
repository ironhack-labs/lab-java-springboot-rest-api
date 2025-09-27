package com.example.labjavaspringbootrestapi.controller;

import com.example.labjavaspringbootrestapi.model.Product;
import com.example.labjavaspringbootrestapi.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private static final String API_KEY = "123456";

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Middleware-like check for API Key
    private boolean isAuthorized(String apiKey) {
        return API_KEY.equals(apiKey);
    }

    @PostMapping
    public ResponseEntity<?> addProduct(
            @RequestHeader(value = "API-Key", required = true) String apiKey,
            @Valid @RequestBody Product product) {
        if (!isAuthorized(apiKey)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid API Key");
        }
        productService.addProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body("Product added successfully");
    }

    @GetMapping
    public ResponseEntity<?> getAllProducts(
            @RequestHeader(value = "API-Key", required = true) String apiKey) {
        if (!isAuthorized(apiKey)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid API Key");
        }
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{name}")
    public ResponseEntity<?> getProductByName(
            @RequestHeader(value = "API-Key", required = true) String apiKey,
            @PathVariable String name) {
        if (!isAuthorized(apiKey)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid API Key");
        }
        return productService.getProductByName(name)
                .<ResponseEntity<Object>>map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found"));
    }

    @PutMapping("/{name}")
    public ResponseEntity<?> updateProduct(
            @RequestHeader(value = "API-Key", required = true) String apiKey,
            @PathVariable String name,
            @Valid @RequestBody Product product) {
        if (!isAuthorized(apiKey)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid API Key");
        }
        boolean updated = productService.updateProduct(name, product);
        if (updated) {
            return ResponseEntity.ok("Product updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<?> deleteProduct(
            @RequestHeader(value = "API-Key", required = true) String apiKey,
            @PathVariable String name) {
        if (!isAuthorized(apiKey)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid API Key");
        }
        boolean deleted = productService.deleteProduct(name);
        if (deleted) {
            return ResponseEntity.ok("Product deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<?> getProductsByCategory(
            @RequestHeader(value = "API-Key", required = true) String apiKey,
            @PathVariable String category) {
        if (!isAuthorized(apiKey)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid API Key");
        }
        List<Product> products = productService.getProductsByCategory(category);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/price")
    public ResponseEntity<?> getProductsByPriceRange(
            @RequestHeader(value = "API-Key", required = true) String apiKey,
            @RequestParam double min,
            @RequestParam double max) {
        if (!isAuthorized(apiKey)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid API Key");
        }
        List<Product> products = productService.getProductsByPriceRange(min, max);
        return ResponseEntity.ok(products);
    }
}