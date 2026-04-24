package com.ironhack.labjavaspringbootrestapi.controller;

import com.ironhack.labjavaspringbootrestapi.model.Product;
import com.ironhack.labjavaspringbootrestapi.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ironhack.labjavaspringbootrestapi.exception.InvalidApiKeyException;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {

        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(
            @RequestHeader("API-Key") String apiKey,
            @RequestBody @Valid Product product) {

        validateApiKey(apiKey);
        Product createdProduct = productService.addProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(
            @RequestHeader("API-Key") String apiKey) {

        validateApiKey(apiKey);
        return ResponseEntity.ok(productService.getAllProducts());
    }
    @GetMapping("/{name}")
    public ResponseEntity<Product> getProductByName(
            @RequestHeader("API-Key") String apiKey,
            @PathVariable String name) {

        validateApiKey(apiKey);
        Product product = productService.getProductByName(name);
        return ResponseEntity.ok(product);
    }

    @PutMapping("/{name}")
    public ResponseEntity<Product> updateProduct(
            @RequestHeader("API-Key") String apiKey,
            @PathVariable String name,
            @RequestBody @Valid Product product) {

        validateApiKey(apiKey);
        Product updatedProduct = productService.updateProduct(name, product);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Void> deleteProduct(
            @RequestHeader("API-Key") String apiKey,
            @PathVariable String name) {

        validateApiKey(apiKey);
        productService.deleteProduct(name);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Product>> getProductsByCategory(
            @RequestHeader("API-Key") String apiKey,
            @PathVariable String category) {

        validateApiKey(apiKey);
        return ResponseEntity.ok(productService.getProductsByCategory(category));
    }

    @GetMapping("/price")
    public ResponseEntity<List<Product>> getProductsByPriceRange(
            @RequestHeader("API-Key") String apiKey,
            @RequestParam double min,
            @RequestParam double max) {

        validateApiKey(apiKey);
        return ResponseEntity.ok(productService.getProductsByPriceRange(min, max));
    }

//Metodo para validad el apikey
    private void validateApiKey(String apiKey) {
        if (!"123456".equals(apiKey)) {
            throw new InvalidApiKeyException("Invalid API-Key");
        }
    }
}
