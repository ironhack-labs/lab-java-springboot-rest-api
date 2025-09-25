package com.springboot_lab.controller;

import com.springboot_lab.exception.InvalidApiKeyException;
import com.springboot_lab.exception.ProductNotFoundException;
import com.springboot_lab.model.Product;
import com.springboot_lab.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private static final String REQUIRED_API_KEY = "123456";

    // Constructor injection
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(
            @RequestHeader(value = "API-Key", required = false) String apiKey,
            @Valid @RequestBody Product product) {
        validateApiKey(apiKey);
        Product createdProduct = productService.addProduct(product);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(@RequestHeader(value = "API-Key", required = false) String apiKey) {
        validateApiKey(apiKey);
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{name}")
    public ResponseEntity<Product> getProductByName(
            @RequestHeader(value = "API-Key", required = false) String apiKey,
            @PathVariable String name) {
        validateApiKey(apiKey);
        return productService.getProductByName(name)
                .map(product -> ResponseEntity.ok(product))
                .orElseThrow(() -> new ProductNotFoundException("Product with name '" + name + "' not found"));
    }

    @PutMapping("/{name}")
    public ResponseEntity<Product> updateProduct(
            @RequestHeader(value = "API-Key", required = false) String apiKey,
            @PathVariable String name,
            @Valid @RequestBody Product product) {
        validateApiKey(apiKey);
        return productService.updateProduct(name, product)
                .map(updatedProduct -> ResponseEntity.ok(updatedProduct))
                .orElseThrow(() -> new ProductNotFoundException("Product with name '" + name + "' not found"));
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Void> deleteProduct(
            @RequestHeader(value = "API-Key", required = false) String apiKey,
            @PathVariable String name) {
        validateApiKey(apiKey);
        boolean deleted = productService.deleteProduct(name);
        if (!deleted) {
            throw new ProductNotFoundException("Product with name '" + name + "' not found");
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Product>> getProductsByCategory(
            @RequestHeader(value = "API-Key", required = false) String apiKey,
            @PathVariable String category) {
        validateApiKey(apiKey);
        List<Product> products = productService.getProductsByCategory(category);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/price")
    public ResponseEntity<List<Product>> getProductsByPriceRange(
            @RequestHeader(value = "API-Key", required = false) String apiKey,
            @RequestParam Double min,
            @RequestParam Double max) {
        validateApiKey(apiKey);
        List<Product> products = productService.getProductsByPriceRange(min, max);
        return ResponseEntity.ok(products);
    }


    private void validateApiKey(String apiKey) {
        if (!REQUIRED_API_KEY.equals(apiKey)) {
            throw new InvalidApiKeyException("Invalid or missing API-Key header");
        }
    }
}
