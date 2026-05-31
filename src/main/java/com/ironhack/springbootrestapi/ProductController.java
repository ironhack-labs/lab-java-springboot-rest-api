package com.ironhack.springbootrestapi;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    private void checkApiKey(String apiKey) {
        if (apiKey == null || !apiKey.equals("123456")) {
            throw new MissingApiKeyException("API-Key is missing or wrong");
        }
    }

    @PostMapping("/products")
    public Product addProduct(@RequestHeader(value = "API-Key", required = false) String apiKey,
                              @Valid @RequestBody Product product) {
        checkApiKey(apiKey);
        return productService.addProduct(product);
    }

    @GetMapping("/products")
    public List<Product> getAllProducts(@RequestHeader(value = "API-Key", required = false) String apiKey) {
        checkApiKey(apiKey);
        return productService.getAllProducts();
    }

    @GetMapping("/products/{name}")
    public Product getProductByName(@RequestHeader(value = "API-Key", required = false) String apiKey,
                                    @PathVariable String name) {
        checkApiKey(apiKey);
        return productService.getProductByName(name);
    }

    @PutMapping("/products/{name}")
    public Product updateProduct(@RequestHeader(value = "API-Key", required = false) String apiKey,
                                 @PathVariable String name,
                                 @Valid @RequestBody Product product) {
        checkApiKey(apiKey);
        return productService.updateProduct(name, product);
    }

    @DeleteMapping("/products/{name}")
    public String deleteProduct(@RequestHeader(value = "API-Key", required = false) String apiKey,
                                @PathVariable String name) {
        checkApiKey(apiKey);
        return productService.deleteProduct(name);
    }

    @GetMapping("/products/category/{category}")
    public List<Product> getProductsByCategory(@RequestHeader(value = "API-Key", required = false) String apiKey,
                                               @PathVariable String category) {
        checkApiKey(apiKey);
        return productService.getProductsByCategory(category);
    }

    @GetMapping("/products/price")
    public List<Product> getProductsByPriceRange(@RequestHeader(value = "API-Key", required = false) String apiKey,
                                                 @RequestParam double min,
                                                 @RequestParam double max) {
        checkApiKey(apiKey);
        return productService.getProductsByPriceRange(min, max);
    }
}