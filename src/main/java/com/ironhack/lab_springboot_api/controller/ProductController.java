package com.ironhack.lab_springboot_api.controller;

import com.ironhack.lab_springboot_api.model.Product;
import com.ironhack.lab_springboot_api.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<?> createProduct(
            @RequestHeader(value = "API-Key", required = false) String apiKey,
            @Valid @RequestBody Product product) {
        if (apiKey == null || !apiKey.equals("123456")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid API Key");
        }
        return new ResponseEntity<>(productService.addProduct(product), HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<?> getProducts(@RequestHeader String apiKey) {
        if (apiKey == null || !apiKey.equals("123456")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid API Key");
        }
        return ResponseEntity.ok(productService.getProductList());
    }

    @GetMapping("/{name}")
    public ResponseEntity<?> getProduct(@PathVariable String name,
                                        @RequestHeader("API-Key") String apiKey) {

        if (apiKey == null || !apiKey.equals("123456")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid API Key");
        }

        Product product = productService.getProductByName(name);


        if (product == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado");
        }

        return ResponseEntity.ok(product);
    }

    @GetMapping("/price")
    public ResponseEntity<?> getProductPrice(
            @RequestHeader("API-Key") String apiKey,
            @RequestParam double min,
            @RequestParam double max) {

        if (apiKey == null || !apiKey.equals("123456")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid API Key");
        }
        return ResponseEntity.ok(productService.getByPriceRange(min, max));
    }

    // 1. Obtener por categoría
    @GetMapping("/category/{category}")
    public ResponseEntity<?> getProductByCategory(@RequestHeader("API-Key") String apiKey, @PathVariable String category) {
        if (apiKey == null || !apiKey.equals("123456")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid API Key");
        }
        return ResponseEntity.ok(productService.getByCategory(category));
    }

    @PutMapping("/{name}")
    public ResponseEntity<?> updateProduct(@RequestHeader("API-Key") String apiKey,
                                           @PathVariable String name,
                                           @Valid @RequestBody Product product) {
        if (apiKey == null || !apiKey.equals("123456")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid API Key");
        }
        Product updated = productService.updateProduct(name, product);
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<?> deleteProduct(@RequestHeader("API-Key") String apiKey, @PathVariable String name) {
        if (apiKey == null || !apiKey.equals("123456")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid API Key");
        }
        productService.deleteProduct(name);
        return ResponseEntity.noContent().build();
    }



}
