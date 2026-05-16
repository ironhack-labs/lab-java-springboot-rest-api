package com.ironhack.lab.controller;

import com.ironhack.lab.exception.ApiKeyMissingException;
import com.ironhack.lab.exception.InvalidPriceRangeException;
import com.ironhack.lab.exception.ProductNotFoundException;
import com.ironhack.lab.model.Product;
import com.ironhack.lab.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private static final String API_KEY = "123456";

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    private void validateApiKey(String apiKey) {
        if (apiKey == null || !apiKey.equals(API_KEY)) {
            throw new ApiKeyMissingException("Invalid or missing API-Key header");
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestHeader("API-Key") String apiKey,
                                 @Valid @RequestBody Product product) {
        validateApiKey(apiKey);
        return productService.addProduct(product);
    }

    @GetMapping
    public List<Product> getAllProducts(@RequestHeader("API-Key") String apiKey) {
        validateApiKey(apiKey);
        return productService.getAllProducts();
    }

    @GetMapping("/{name}")
    public Product getProductByName(@RequestHeader("API-Key") String apiKey,
                                    @PathVariable String name) {
        validateApiKey(apiKey);
        return productService.getProductByName(name)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with name: " + name));
    }

    @PutMapping("/{name}")
    public Product updateProduct(@RequestHeader("API-Key") String apiKey,
                                @PathVariable String name,
                                @Valid @RequestBody Product product) {
        validateApiKey(apiKey);
        return productService.updateProduct(name, product)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with name: " + name));
    }

    @DeleteMapping("/{name}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@RequestHeader("API-Key") String apiKey,
                             @PathVariable String name) {
        validateApiKey(apiKey);
        boolean deleted = productService.deleteProduct(name);
        if (!deleted) {
            throw new ProductNotFoundException("Product not found with name: " + name);
        }
    }

    @GetMapping("/category/{category}")
    public List<Product> getProductsByCategory(@RequestHeader("API-Key") String apiKey,
                                               @PathVariable String category) {
        validateApiKey(apiKey);
        return productService.getProductsByCategory(category);
    }

    @GetMapping("/price")
    public List<Product> getProductsByPriceRange(@RequestHeader("API-Key") String apiKey,
                                                 @RequestParam Double min,
                                                 @RequestParam Double max) {
        validateApiKey(apiKey);
        if (min < 0 || max < 0 || min > max) {
            throw new InvalidPriceRangeException("Invalid price range: min must be less than or equal to max and both must be positive");
        }
        return productService.getProductsByPriceRange(min, max);
    }
}

