package com.ironhack.lab_java_springboot_rest_api.controller;

import com.ironhack.lab_java_springboot_rest_api.exception.InvalidPriceRangeException;
import com.ironhack.lab_java_springboot_rest_api.exception.MissingApiKeyException;
import com.ironhack.lab_java_springboot_rest_api.exception.ProductNotFoundException;
import com.ironhack.lab_java_springboot_rest_api.model.Product;
import com.ironhack.lab_java_springboot_rest_api.service.ProductService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
            throw new MissingApiKeyException();
        }
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(
            @RequestHeader(value = "API-Key", required = false) String apiKey,
            @Valid @RequestBody Product product) {

        validateApiKey(apiKey);
        Product created = productService.addProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(
            @RequestHeader(value = "API-Key", required = false) String apiKey) {

        validateApiKey(apiKey);
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{name}")
    public ResponseEntity<Product> getProductByName(
            @RequestHeader(value = "API-Key", required = false) String apiKey,
            @PathVariable String name) {

        validateApiKey(apiKey);
        Product product = productService.getProductByName(name);
        if (product == null) {
            throw new ProductNotFoundException(name);
        }
        return ResponseEntity.ok(product);
    }

    @PutMapping("/{name}")
    public ResponseEntity<Product> updateProduct(
            @RequestHeader(value = "API-Key", required = false) String apiKey,
            @PathVariable String name,
            @Valid @RequestBody Product updatedProduct) {

        validateApiKey(apiKey);
        Product product = productService.updateProduct(name, updatedProduct);
        if (product == null) {
            throw new ProductNotFoundException(name);
        }
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Void> deleteProduct(
            @RequestHeader(value = "API-Key", required = false) String apiKey,
            @PathVariable String name) {

        validateApiKey(apiKey);
        boolean deleted = productService.deleteProduct(name);
        if (!deleted) {
            throw new ProductNotFoundException(name);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Product>> getProductsByCategory(
            @RequestHeader(value = "API-Key", required = false) String apiKey,
            @PathVariable String category) {

        validateApiKey(apiKey);
        return ResponseEntity.ok(productService.getProductsByCategory(category));
    }

    @GetMapping("/price")
    public ResponseEntity<List<Product>> getProductsByPriceRange(
            @RequestHeader(value = "API-Key", required = false) String apiKey,
            @RequestParam double min,
            @RequestParam double max) {

        validateApiKey(apiKey);
        if (min > max) {
            throw new InvalidPriceRangeException();
        }
        return ResponseEntity.ok(productService.getProductsByPriceRange(min, max));
    }
}

//    @PostMapping
//    public ResponseEntity<Product> createProduct(
//            @RequestHeader(value = "API-Key", required = false) String apiKey,
//            @Valid @RequestBody @NotNull Product product) {
//        validateApiKey(apiKey);
//        Product existingProduct = productService.getProductByName(product.getName());
//        // Check if product & update the qty.
//        if (existingProduct != null) {
//             existingProduct.setQuantity(existingProduct.getQuantity() + product.getQuantity());
//             Product updatedProduct = productService.updateProduct(existingProduct.getName(), existingProduct);
//             return ResponseEntity.ok(updatedProduct);
//        }
//
//        Product created = productService.addProduct(product);
//        return ResponseEntity.status(HttpStatus.CREATED).body(created);
//    }

//        Check if a product with the same name already exists but not update the qty.
//        Product existingProduct = productService.getProductByName(product.getName());
//        if (existingProduct != null) {
//            throw new IllegalArgumentException("Product with name " + product.getName() + " already exists.");
//        }