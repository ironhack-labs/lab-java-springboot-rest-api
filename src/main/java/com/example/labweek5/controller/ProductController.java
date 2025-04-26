package com.example.labweek5.controller;

import com.example.labweek5.model.Product;
import com.example.labweek5.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Product> creaateProduct(@RequestHeader("API-Key")String apikey, @Valid @RequestBody Product product) {
        if (apikey.equals("123456")){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        productService.addProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }
    @GetMapping("/{name}")
    public Product getProductByName(@PathVariable String name) {
        return productService.getProductByName(name);
    }
    @PutMapping("/{name}")
    public ResponseEntity<Product> updateProduct(@PathVariable String name, @Valid @RequestBody Product product) {
        productService.uptadeProduct(name, product);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @DeleteMapping("/{name}")
    public ResponseEntity<Product> deleteProduct(@PathVariable String name) {
        productService.deleteProduct(name);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/category/{category}")
    public List<Product> getProductsByCategory(@PathVariable String category) {
        return productService.getProductByCategory(category);
    }
    @GetMapping("/price")
    public List<Product> getProductsByPriceRange(@RequestParam double min, @RequestParam double max) {
        return productService.getProductByPriceRange(min, max);
    }
}

