package com.ironhack.labjavaspringbootrestapi.controller;

import com.ironhack.labjavaspringbootrestapi.exception.InvalidApiKeyException;
import com.ironhack.labjavaspringbootrestapi.model.Product;
import com.ironhack.labjavaspringbootrestapi.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private static final String VALID_API_KEY = "123456";

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    private void validateApiKey(String apiKey) {
        if (!VALID_API_KEY.equals(apiKey)) {
            throw new InvalidApiKeyException("Invalid API Key");
        }
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestHeader("API-Key") String apiKey, @Valid @RequestBody Product product) {
        validateApiKey(apiKey);
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.create(product));
    }

    @GetMapping
    public List<Product> getAllProducts(@RequestHeader("API-Key") String apiKey) {
        validateApiKey(apiKey);
        return productService.findAll();
    }

    @GetMapping("/{name}")
    public Product getProductByName(@RequestHeader("API-Key") String apiKey, @PathVariable String name) {
        validateApiKey(apiKey);
        return productService.findByName(name);
    }

    @PutMapping("/{name}")
    public Product updateProduct(@RequestHeader("API-Key") String apiKey, @PathVariable String name, @Valid @RequestBody Product product) {
        validateApiKey(apiKey);
        return productService.update(name, product);
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Void> deleteProduct(@RequestHeader("API-Key") String apiKey, @PathVariable String name) {
        validateApiKey(apiKey);
        productService.delete(name);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/category/{category}")
    public List<Product> getProductsByCategory(@RequestHeader("API-Key") String apiKey, @PathVariable String category) {
        validateApiKey(apiKey);
        return productService.findByCategory(category);
    }

    @GetMapping("/price")
    public List<Product> getProductsByPriceRange(@RequestHeader("API-Key") String apiKey, @RequestParam double min, @RequestParam double max) {
        validateApiKey(apiKey);
        return productService.findByPriceRange(min, max);
    }
}