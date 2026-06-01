package com.ironhack.lab.controller;

import com.ironhack.lab.exception.InvalidApiKeyException;
import com.ironhack.lab.model.Product;
import com.ironhack.lab.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private static final String VALID_API_KEY = "123456";
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
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
        return productService.getProductByName(name);
    }

    @PutMapping("/{name}")
    public Product updateProduct(
            @RequestHeader("API-Key") String apiKey,
            @PathVariable String name,
            @Valid @RequestBody Product product) {
        validateApiKey(apiKey);
        return productService.updateProduct(name, product);
    }

    @DeleteMapping("/{name}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@RequestHeader("API-Key") String apiKey, @PathVariable String name) {
        validateApiKey(apiKey);
        productService.deleteProduct(name);
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
        return productService.getProductsByPriceRange(min, max);
    }

    private void validateApiKey(String apiKey) {
        if (!VALID_API_KEY.equals(apiKey)) {
            throw new InvalidApiKeyException();
        }
    }
}
