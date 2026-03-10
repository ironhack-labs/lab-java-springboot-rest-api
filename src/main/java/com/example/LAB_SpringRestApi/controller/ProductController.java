package com.example.LAB_SpringRestApi.controller;

import com.example.LAB_SpringRestApi.model.Product;
import com.example.LAB_SpringRestApi.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    private void validateApiKey(String apiKey) {
        if (!"123456".equals(apiKey)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid API-Key");
        }
    }

    @PostMapping
    public ResponseEntity<Product> create(@Valid @RequestBody Product product, @RequestHeader("API-Key") String apiKey) {
        validateApiKey(apiKey);
        productService.addProduct(product);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Product> getAll(@RequestHeader("API-Key") String apiKey) {
        validateApiKey(apiKey);
        return productService.getAllProducts();
    }

    @GetMapping("/{name}")
    public Product getByName(@PathVariable String name, @RequestHeader("API-Key") String apiKey) {
        validateApiKey(apiKey);
        return productService.getByName(name)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
    }

    @PutMapping("/{name}")
    public String update(@PathVariable String name, @Valid @RequestBody Product product, @RequestHeader("API-Key") String apiKey) {
        validateApiKey(apiKey);
        if (productService.updateProduct(name, product)) return "Product updated successfully";
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
    }

    @DeleteMapping("/{name}")
    public String delete(@PathVariable String name, @RequestHeader("API-Key") String apiKey) {
        validateApiKey(apiKey);
        if (productService.deleteProduct(name)) return "Product deleted";
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
    }

    @GetMapping("/category/{category}")
    public List<Product> getByCategory(@PathVariable String category, @RequestHeader("API-Key") String apiKey) {
        validateApiKey(apiKey);
        return productService.getByCategory(category);
    }

    @GetMapping("/price")
    public List<Product> getByPriceRange(@RequestParam double min, @RequestParam double max, @RequestHeader("API-Key") String apiKey) {
        validateApiKey(apiKey);
        if (min > max) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid price range");
        return productService.getByPriceRange(min, max);
    }
}