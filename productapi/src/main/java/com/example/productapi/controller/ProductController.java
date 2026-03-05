package com.example.productapi.controller;

import com.example.productapi.model.Product;
import com.example.productapi.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    private void checkApiKey(String apiKey){
        if(apiKey == null || !apiKey.equals("123456")){
            throw new RuntimeException("Invalid API Key");
        }
    }

    @PostMapping
    public Product createProduct(
            @RequestHeader("API-Key") String apiKey,
            @Valid @RequestBody Product product){

        checkApiKey(apiKey);
        return productService.addProduct(product);
    }

    @GetMapping
    public List<Product> getAllProducts(@RequestHeader("API-Key") String apiKey){
        checkApiKey(apiKey);
        return productService.getAllProducts();
    }

    @GetMapping("/{name}")
    public Product getProduct(
            @RequestHeader("API-Key") String apiKey,
            @PathVariable String name){

        checkApiKey(apiKey);
        return productService.getProductByName(name);
    }

    @PutMapping("/{name}")
    public Product updateProduct(
            @RequestHeader("API-Key") String apiKey,
            @PathVariable String name,
            @Valid @RequestBody Product product){

        checkApiKey(apiKey);
        return productService.updateProduct(name, product);
    }

    @DeleteMapping("/{name}")
    public void deleteProduct(
            @RequestHeader("API-Key") String apiKey,
            @PathVariable String name){

        checkApiKey(apiKey);
        productService.deleteProduct(name);
    }

    @GetMapping("/category/{category}")
    public List<Product> getByCategory(
            @RequestHeader("API-Key") String apiKey,
            @PathVariable String category){

        checkApiKey(apiKey);
        return productService.getProductsByCategory(category);
    }

    @GetMapping("/price")
    public List<Product> getByPriceRange(
            @RequestHeader("API-Key") String apiKey,
            @RequestParam double min,
            @RequestParam double max){

        checkApiKey(apiKey);
        return productService.getProductsByPriceRange(min, max);
    }
}