package com.crud.crudlab5.controller;

import com.crud.crudlab5.model.Product;
import com.crud.crudlab5.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
class ProductController {
    private final ProductService productService;
    private static final String API_KEY = "123456";


    ProductController(ProductService productService) {
        this.productService = productService;
    }

    private void checkApiKey(String key) {
        if (key == null || !API_KEY.equals(key)) {
            throw new RuntimeException("Invalid API key");
        }
    }

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestHeader("API-Key") String key, @Valid @RequestBody Product product) {
        checkApiKey(key);

        return new ResponseEntity<>(productService.addProduct(product), HttpStatus.CREATED);
    }

    @GetMapping
    public List<Product> getALL(@RequestHeader("API-Key") String key) {
        checkApiKey(key);
        return productService.getAllProducts();

    }
    @GetMapping("/{name}")
    public Product getByName(@RequestHeader("API-Key") String key, @PathVariable String name) {
        checkApiKey(key);
        return productService.getProductByName(name);
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Product> delete (@RequestHeader ("API-Key") String key, @PathVariable String name) {
        checkApiKey(key);
        productService.deleteProduct(name);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/category/{category}")
    public List<Product>getbyCategory(@RequestHeader("API-Key") String key, @PathVariable String category) {
        checkApiKey(key);
        return productService.getProductsByCategory(category);
    }

    @GetMapping("/price")
    public List<Product> getByPrice(@RequestHeader("API-Key") String key,@RequestParam double min,@RequestParam double max) {
        checkApiKey(key);
        return productService.getProductsByPriceRange(min,max);
    }


    }

