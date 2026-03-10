package com.ironhack.controllers;

import com.ironhack.models.Product;
import com.ironhack.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    private void validateApiKey(String apiKey) {
        if (!"123456".equals(apiKey)) {
            throw new RuntimeException("Invalid API Key");
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestHeader("API-Key") String apiKey, @Valid @RequestBody Product product) {
        validateApiKey(apiKey);
        return productService.addProduct(product);
    }

    @GetMapping
    public List<Product> getAllProducts(@RequestHeader("API-Key") String apiKey) {
        validateApiKey(apiKey);
        return productService.getAllProducts();
    }

    @GetMapping("/{name}")
    public Product getProductByName(@RequestHeader("API-Key") String apiKey, @PathVariable String name) {
        validateApiKey(apiKey);
        Optional<Product> product = productService.getProductByName(name);

        if (product.isEmpty()) {
            throw new RuntimeException("Product not found");
        }
        return product.get();
    }

    @PutMapping("/{name}")
    public Product updateProduct(@RequestHeader("API-Key") String apiKey, @PathVariable String name, @Valid @RequestBody Product product) {
        validateApiKey(apiKey);
        Product updated = productService.updateProduct(name, product);

        if (updated == null) {
            throw new RuntimeException("Product not found");
        }
        return updated;
    }

    @DeleteMapping("/{name}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@RequestHeader("API-Key") String apiKey, @PathVariable String name) {
        validateApiKey(apiKey);
        boolean deleted = productService.deleteProduct(name);
        if (!deleted) {
            throw new RuntimeException("Product not found");
        }
    }

    @GetMapping("/category/{category}")
    public List<Product> getProductsByCategory(@RequestHeader("API-Key") String apiKey, @PathVariable String category) {
        validateApiKey(apiKey);
        return productService.getProductsByCategory(category);
    }

    @GetMapping("/price")
    public List<Product> getProductsByPriceRange(
            @RequestHeader("API-Key") String apiKey,
            @RequestParam double min,
            @RequestParam double max) {

        validateApiKey(apiKey);

        if (min < 0 || max < 0 || min > max) {
            throw new RuntimeException("Invalid price range");
        }

        return productService.getProductsByPriceRange(min, max);
    }
}