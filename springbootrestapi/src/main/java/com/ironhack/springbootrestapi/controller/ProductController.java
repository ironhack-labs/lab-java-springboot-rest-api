package com.ironhack.springbootrestapi.controller;

import com.ironhack.springbootrestapi.exception.InvalidAPIKey;
import com.ironhack.springbootrestapi.model.Product;
import com.ironhack.springbootrestapi.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    String key = "123456";

    private void validateAPIKey(String apiKey) {
        if(apiKey == null || apiKey.trim().isEmpty()) {
            throw new InvalidAPIKey("API key is required");
        }
        if(!apiKey.equals(key)) {
            throw new InvalidAPIKey("Invalid API key");
        }
    }

    @GetMapping
    public List<Product> getAllProducts(@RequestHeader(name ="API-Key", required = false) String apiKey) {
        validateAPIKey(apiKey);
        return productService.getAllProducts();
    }

    @GetMapping("/{name}")
    public Product getProductByName(@RequestHeader(name ="API-Key", required = false) String apiKey, @PathVariable String name) {
        validateAPIKey(apiKey);
        return productService.getProductByName(name);
    }

    @GetMapping("/category/{category}")
    public List<Product> getProductsByCategory(@RequestHeader(name ="API-Key", required = false) String apiKey, @PathVariable String category) {
        validateAPIKey(apiKey);
        return productService.getProductsByCategory(category);
    }

    @GetMapping("/price")
    public List<Product> getProductsByPriceRange(@RequestHeader(name ="API-Key", required = false) String apiKey, @RequestParam BigDecimal min, @RequestParam BigDecimal max) {
        validateAPIKey(apiKey);
        return productService.getProductsByPriceRange(min, max);
    }

    @PostMapping
    public Product addProduct(@RequestHeader(name ="API-Key", required = false) String apiKey, @RequestBody Product product) {
        validateAPIKey(apiKey);
        return productService.addProduct(product);
    }

    @PutMapping("/{name}")
    public Product updateProduct(@RequestHeader(name ="API-Key", required = false) String apiKey, @PathVariable String name, @RequestBody Product product) {
        validateAPIKey(apiKey);
        return productService.updateProduct(name, product);
    }

    @DeleteMapping("/{name}")
    public Product deleteProduct(@RequestHeader(name ="API-Key", required = false) String apiKey, @PathVariable String name) {
        validateAPIKey(apiKey);
        return productService.deleteProduct(name);
    }
}
