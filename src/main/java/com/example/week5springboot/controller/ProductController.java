package com.example.week5springboot.controller;

import com.example.week5springboot.dto.ProductDTO;
import com.example.week5springboot.model.Product;
import com.example.week5springboot.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private static final String VALID_API_KEY = "123456";

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    private boolean isInvalidKey(String apiKey) {
        return !VALID_API_KEY.equals(apiKey);
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(
            @RequestHeader(value = "API-Key", required = false) String apiKey,
            @Valid @RequestBody ProductDTO productDTO) {

        if (isInvalidKey(apiKey)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Product saved = productService.addProduct(productDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(@RequestHeader("API-Key") String apiKey) {
        if (isInvalidKey(apiKey)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{name}")
    public ResponseEntity<Product> getProductByName(
            @RequestHeader("API-Key") String apiKey,
            @PathVariable String name) {

        if (isInvalidKey(apiKey)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Product product = productService.getProductByName(name);
        return ResponseEntity.ok(product);
    }

    @PutMapping("/{name}")
    public ResponseEntity<Product> updateProduct(
            @RequestHeader("API-Key") String apiKey,
            @PathVariable String name,
            @Valid @RequestBody ProductDTO productDTO) {

        if (isInvalidKey(apiKey)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Product updated = productService.updateProduct(name, productDTO);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Void> deleteProduct(
            @RequestHeader("API-Key") String apiKey,
            @PathVariable String name) {

        if (isInvalidKey(apiKey)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        boolean deleted = productService.deleteProduct(name);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Product>> getProductsByCategory(
            @RequestHeader("API-Key") String apiKey,
            @PathVariable String category) {

        if (isInvalidKey(apiKey)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(productService.getProductsByCategory(category));
    }

    @GetMapping("/price")
    public ResponseEntity<List<Product>> getProductsByPriceRange(
            @RequestHeader("API-Key") String apiKey,
            @RequestParam double min,
            @RequestParam double max) {

        if (isInvalidKey(apiKey)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        if (min > max) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(productService.getProductsByPriceRange(min, max));
    }
}