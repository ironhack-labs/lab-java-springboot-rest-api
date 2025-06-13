package com.example.products_api;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
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

    @ModelAttribute
    public void checkApiKey(@RequestHeader("API-Key") String apiKey) {
        if (!"123456".equals(apiKey)) {
            throw new SecurityException("Invalid API Key");
        }
    }

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody Product product) {
        productService.addProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public List<Product> getAll() {
        return productService.getAll();
    }

    @GetMapping("/{name}")
    public Product getByName(@PathVariable String name) {
        return productService.getByName(name);
    }

    @PutMapping("/{name}")
    public ResponseEntity<Void> update(@PathVariable String name, @Valid @RequestBody Product product) {
        productService.update(name, product);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Void> delete(@PathVariable String name) {
        productService.delete(name);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/category/{category}")
    public List<Product> getByCategory(@PathVariable String category) {
        return productService.getByCategory(category);
    }

    @GetMapping("/price")
    public List<Product> getByPriceRange(@RequestParam double min, @RequestParam double max) {
        return productService.getByPriceRange(min, max);
    }
}
