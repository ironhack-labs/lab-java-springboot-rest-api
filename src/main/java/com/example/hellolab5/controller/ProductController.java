package com.example.hellolab5.controller;

import com.example.hellolab5.Product;
import com.example.hellolab5.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestHeader("API-Key") String key, @Valid @RequestBody Product p) {
        validateKey(key);
        service.add(p);
        return ResponseEntity.ok("Product created");
    }

    @GetMapping
    public List<Product> getAll(@RequestHeader("API-Key") String key) {
        validateKey(key);
        return service.getAll();
    }

    @GetMapping("/{name}")
    public Product getByName(@RequestHeader("API-Key") String key, @PathVariable String name) {
        validateKey(key);
        return service.getByName(name).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @PutMapping("/{name}")
    public ResponseEntity<String> update(@RequestHeader("API-Key") String key, @PathVariable String name, @Valid @RequestBody Product p) {
        validateKey(key);
        service.update(name, p);
        return ResponseEntity.ok("Product updated");
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<String> delete(@RequestHeader("API-Key") String key, @PathVariable String name) {
        validateKey(key);
        service.delete(name);
        return ResponseEntity.ok("Product deleted");
    }

    @GetMapping("/category/{category}")
    public List<Product> getByCategory(@RequestHeader("API-Key") String key, @PathVariable String category) {
        validateKey(key);
        return service.getByCategory(category);
    }

    @GetMapping("/price")
    public List<Product> getByPrice(@RequestHeader("API-Key") String key, @RequestParam double min, @RequestParam double max) {
        validateKey(key);
        return service.getByPriceRange(min, max);
    }

    private void validateKey(String key) {
        if (!"123456".equals(key)) throw new RuntimeException("Invalid API-Key");
    }
}