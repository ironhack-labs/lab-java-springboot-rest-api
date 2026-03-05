package com.fairtok.online_shopping.controller;

import com.fairtok.online_shopping.classes.Product;
import com.fairtok.online_shopping.service.ApiService;
import com.fairtok.online_shopping.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final ApiService apiService;
    public ProductController(ProductService productService, ApiService apiService){
        this.productService = productService;
        this.apiService = apiService;
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product, @RequestHeader(value = "API-Key", required = false) String apiKey){
        apiService.isValidApiKey(apiKey);
        Product created = productService.create(product.getName(), product.getPrice(), product.getCategory(), product.getQuantity());
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{name}")
    public ResponseEntity<Product> updateProduct(@PathVariable String name, @Valid @RequestBody Product product, @RequestHeader(value = "API-Key", required = false) String apiKey){
        apiService.isValidApiKey(apiKey);
        Product updated = productService.update(name, product.getPrice(), product.getCategory(), product.getQuantity());
        if (updated == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Void> updateProduct(@PathVariable String name, @RequestHeader(value = "API-Key", required = false) String apiKey){
        apiService.isValidApiKey(apiKey);
        productService.delete(name);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public List<Product> allProducts(@RequestHeader(value = "API-Key", required = false) String apiKey){
        apiService.isValidApiKey(apiKey);
        return productService.listProducts();
    }

    @GetMapping("/{name}")
    public Product listByName(@PathVariable String name, @RequestHeader(value = "API-Key", required = false) String apiKey){
        apiService.isValidApiKey(apiKey);
        return productService.findProductByName(name);
    }

    @GetMapping("/category/{category}")
    public List<Product> listByCategory(@PathVariable String category, @RequestHeader(value = "API-Key", required = false) String apiKey){
        apiService.isValidApiKey(apiKey);
        return productService.findByCategory(category);
    }

    @GetMapping("/price")
    public List<Product> listByPriceRange(@RequestParam double min, @RequestParam double max, @RequestHeader(value = "API-Key", required = false) String apiKey){
        apiService.isValidApiKey(apiKey);
        return productService.findByPriceRange(min, max);
    }
}
