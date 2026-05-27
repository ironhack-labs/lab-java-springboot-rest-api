package com.restapi.demo.controller;

import com.restapi.demo.model.Product;
import com.restapi.demo.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    // Constructor injection
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // POST /products - Create new product
    @PostMapping
    public ResponseEntity<Product> createProduct(
            @RequestHeader("API-Key") String apiKey,
            @Valid @RequestBody Product product) {
        Product created = productService.addProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // GET /products - Get all products
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(
            @RequestHeader("API-Key") String apiKey) {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    // GET /products/{name} - Get product by name
    @GetMapping("/{name}")
    public ResponseEntity<Product> getProductByName(
            @RequestHeader("API-Key") String apiKey,
            @PathVariable String name) {
        return ResponseEntity.ok(productService.getProductByName(name));
    }

    // PUT /products/{name} - Update product
    @PutMapping("/{name}")
    public ResponseEntity<Product> updateProduct(
            @RequestHeader("API-Key") String apiKey,
            @PathVariable String name,
            @Valid @RequestBody Product product) {
        return ResponseEntity.ok(productService.updateProduct(name, product));
    }

    // DELETE /products/{name} - Delete product
    @DeleteMapping("/{name}")
    public ResponseEntity<String> deleteProduct(
            @RequestHeader("API-Key") String apiKey,
            @PathVariable String name) {
        productService.deleteProduct(name);
        return ResponseEntity.ok("Product '" + name + "' deleted successfully");
    }

    // GET /products/category/{category} - Get products by category
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Product>> getProductsByCategory(
            @RequestHeader("API-Key") String apiKey,
            @PathVariable String category) {
        return ResponseEntity.ok(productService.getProductsByCategory(category));
    }

    // GET /products/price?min={min}&max={max} - Get products by price range
    @GetMapping("/price")
    public ResponseEntity<List<Product>> getProductsByPriceRange(
            @RequestHeader("API-Key") String apiKey,
            @RequestParam double min,
            @RequestParam double max) {
        return ResponseEntity.ok(productService.getProductsByPriceRange(min, max));
    }
}