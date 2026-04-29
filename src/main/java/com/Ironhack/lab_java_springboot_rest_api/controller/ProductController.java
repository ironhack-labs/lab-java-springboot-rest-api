package com.Ironhack.lab_java_springboot_rest_api.controller;

import com.Ironhack.lab_java_springboot_rest_api.model.Product;
import com.Ironhack.lab_java_springboot_rest_api.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
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
        if (apiKey == null || !apiKey.equals("123456")) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid API-Key");
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestHeader(value = "API-Key", required = false) String apiKey,
                                 @Valid @RequestBody Product product) {
        validateApiKey(apiKey);
        return productService.addProduct(product);
    }

    @GetMapping
    public List<Product> getAllProducts(@RequestHeader(value = "API-Key", required = false) String apiKey) {
        validateApiKey(apiKey);
        return productService.getAllProducts();
    }

    @GetMapping("/{name}")
    public Product getProductByName(@RequestHeader(value = "API-Key", required = false) String apiKey,
                                    @PathVariable String name) {
        validateApiKey(apiKey);
        Product product = productService.getProductByName(name);
        if (product == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }
        return product;
    }

    @PutMapping("/{name}")
    public Product updateProduct(@RequestHeader(value = "API-Key", required = false) String apiKey,
                                 @PathVariable String name,
                                 @Valid @RequestBody Product product) {
        validateApiKey(apiKey);
        boolean isUpdated = productService.updateProduct(name, product);
        if (!isUpdated) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }
        return product;
    }

    @DeleteMapping("/{name}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@RequestHeader(value = "API-Key", required = false) String apiKey,
                              @PathVariable String name) {
        validateApiKey(apiKey);
        boolean isDeleted = productService.deleteProduct(name);
        if (!isDeleted) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }
    }

    @GetMapping("/category/{category}")
    public List<Product> getProductsByCategory(@RequestHeader(value = "API-Key", required = false) String apiKey,
                                               @PathVariable String category) {
        validateApiKey(apiKey);
        return productService.getProductsByCategory(category);
    }

    @GetMapping("/price")
    public List<Product> getProductsByPriceRange(@RequestHeader(value = "API-Key", required = false) String apiKey,
                                                 @RequestParam double min,
                                                 @RequestParam double max) {
        validateApiKey(apiKey);
        if (min > max || min < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid price range");
        }
        return productService.getProductsByPriceRange(min, max);
    }
}