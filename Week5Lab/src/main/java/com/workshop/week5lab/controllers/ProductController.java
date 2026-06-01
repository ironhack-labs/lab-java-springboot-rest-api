package com.workshop.week5lab.controllers;

import com.workshop.week5lab.InvalidApiKeyException;
import com.workshop.week5lab.ResourceNotFoundException;
import com.workshop.week5lab.models.Product;
import com.workshop.week5lab.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.expression.AccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
public class ProductController {
    private ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    private boolean apiKeyCorrect(String apiKey) {
        return Objects.equals(apiKey, "123456");
    }

    @GetMapping("/products")
    List<Product> getAllProducts(
            @RequestHeader(value = "API-Key", required = true) String apiKey
            ) throws AccessException {
        if (!apiKeyCorrect(apiKey)) {
            throw new InvalidApiKeyException();
        }
        return this.service.allProducts();
    }

    @GetMapping("/products/{name}")
    Product productByName(
            @RequestHeader(value = "API-Key", required = true) String apiKey,
            @PathVariable String name
    ) throws ResourceNotFoundException {
        if (!apiKeyCorrect(apiKey)) {
            throw new InvalidApiKeyException();
        }
        return this.service.productByName(name);
    }

    @GetMapping("/products/category/{category}")
    List<Product> productsByCategory(
            @RequestHeader(value = "API-Key", required = true) String apiKey,
            @PathVariable String category
    ) {
        if (!apiKeyCorrect(apiKey)) {
            throw new InvalidApiKeyException();
        }
        return this.service.productsByCategory(category);
    }

    @GetMapping("/products/price")
    List<Product> productsByCategory(
            @RequestHeader(value = "API-Key", required = true) String apiKey,
            @RequestParam double min,
            @RequestParam double max
    ) {
        if (!apiKeyCorrect(apiKey)) {
            throw new InvalidApiKeyException();
        }
        return this.service.productsByPriceRange(min, max);
    }

    @PostMapping("/products")
    @ResponseStatus(HttpStatus.CREATED)
    Product createProduct(
            @RequestHeader(value = "API-Key", required = true) String apiKey,
            @Valid @RequestBody Product product
    ) throws AccessException {
        if (!apiKeyCorrect(apiKey)) {
            throw new InvalidApiKeyException();
        }
        return this.service.addProduct(product);
    }

    @PutMapping("/products/{name}")
    @ResponseStatus(HttpStatus.CREATED)
    Product updateProduct(
            @RequestHeader(value = "API-Key", required = true) String apiKey,
            @Valid @RequestBody Product product,
            @PathVariable String name
    ) throws AccessException, ResourceNotFoundException {
        if (!apiKeyCorrect(apiKey)) {
            throw new InvalidApiKeyException();
        }
        product.setName(name);
        return this.service.updateProduct(product);
    }

    @DeleteMapping("/products/{name}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteProduct(
            @RequestHeader(value = "API-Key", required = true) String apiKey,
            @PathVariable String name
    ) throws ResourceNotFoundException {
        if (!apiKeyCorrect(apiKey)) {
            throw new InvalidApiKeyException();
        }
        this.service.deleteProduct(name);
    }

}
