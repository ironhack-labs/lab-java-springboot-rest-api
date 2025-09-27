package com.example.lab.controller;

import com.example.lab.exception.ApiKeyException;
import com.example.lab.exception.InvalidPriceRangeException;
import com.example.lab.model.Product;
import com.example.lab.service.ProductService;
import javax.validation.Valid;
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

    private void assertApiKey(String apiKey) {
        if (!"123456".equals(apiKey)) {
            throw new ApiKeyException("Invalid API key");
        }
    }

    @PostMapping
    public ResponseEntity<Product> create(@RequestHeader("API-Key") String apiKey,
                                          @Valid @RequestBody Product product) {
        assertApiKey(apiKey);
        return ResponseEntity.ok(productService.add(product));
    }

    @GetMapping
    public ResponseEntity<List<Product>> all(@RequestHeader("API-Key") String apiKey) {
        assertApiKey(apiKey);
        return ResponseEntity.ok(productService.getAll());
    }

    @GetMapping("/{name}")
    public ResponseEntity<Product> byName(@RequestHeader("API-Key") String apiKey,
                                          @PathVariable String name) {
        assertApiKey(apiKey);
        return ResponseEntity.ok(productService.getByName(name));
    }

    @PutMapping("/{name}")
    public ResponseEntity<Product> update(@RequestHeader("API-Key") String apiKey,
                                          @PathVariable String name,
                                          @Valid @RequestBody Product product) {
        assertApiKey(apiKey);
        return ResponseEntity.ok(productService.update(name, product));
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Void> delete(@RequestHeader("API-Key") String apiKey,
                                       @PathVariable String name) {
        assertApiKey(apiKey);
        productService.delete(name);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Product>> byCategory(@RequestHeader("API-Key") String apiKey,
                                                    @PathVariable String category) {
        assertApiKey(apiKey);
        return ResponseEntity.ok(productService.byCategory(category));
    }

    @GetMapping("/price")
    public ResponseEntity<List<Product>> byPrice(@RequestHeader("API-Key") String apiKey,
                                                 @RequestParam double min,
                                                 @RequestParam double max) {
        assertApiKey(apiKey);
        if (min > max) throw new InvalidPriceRangeException("min must be <= max");
        return ResponseEntity.ok(productService.byPriceRange(min, max));
    }
}
