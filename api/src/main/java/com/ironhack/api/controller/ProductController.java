package com.ironhack.api.controller;

import com.ironhack.api.model.Product;
import com.ironhack.api.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<List<Product>> createProduct(
            @Valid @RequestBody Product product) {
        return ResponseEntity.status(201)
            .body(productService.addProduct(product));
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{name}")
    public ResponseEntity<Product> getProductByName(
            @PathVariable String name) {
        return ResponseEntity.ok(
            productService.getProductByName(name));
    }

    @PutMapping("/{name}")
    public ResponseEntity<List<Product>> updateProduct(
            @PathVariable String name, 
            @Valid @RequestBody Product product) {
        return ResponseEntity.ok(
            productService.updateProduct(name, product));
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<List<Product>> deleteProduct(
            @PathVariable String name) {
        return ResponseEntity.ok(
            productService.deleteProduct(name));
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Product>> getByCategory(
            @PathVariable String category) {
        return ResponseEntity.ok(
            productService.getByCategory(category));
    }

    @GetMapping("/price")
    public ResponseEntity<List<Product>> getByPriceRange(
            @RequestParam Double min, 
            @RequestParam Double max) {
        return ResponseEntity.ok(
            productService.getByPriceRange(min, max));
    }
}
