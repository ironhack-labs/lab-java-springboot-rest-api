package com.example.springbootrestapi.controllers;

import com.example.springbootrestapi.models.Product;
import com.example.springbootrestapi.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("api/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public List<Product> addProduct(@RequestBody Product product, @RequestHeader(name = "API-Key", defaultValue = "123456") String apiKey){
        return productService.addProduct(product);
    }

    @GetMapping
    public List<Product> getAllProduct(@RequestHeader(name = "API-Key", defaultValue = "123456") String apiKey){
        return productService.getAllProduct();
    }

    @GetMapping("/{name}")
    public Product getProductByName(@PathVariable("name") String name,@RequestHeader(name = "API-Key", defaultValue = "123456") String apiKey){
        return productService.getProductsByName(name);
    }
    @PutMapping("/{name}")
    public Product updateProduct(@PathVariable("name") String name, @RequestBody Product product,@RequestHeader(name = "API-Key", defaultValue = "123456") String apiKey){
        return productService.updateProduct(name, product);
    }
    @DeleteMapping("/{name}")
    public List<Product> deleteProduct(@PathVariable("name") String name,@RequestHeader(name = "API-Key", defaultValue = "123456") String apiKey){
        return productService.deleteProduct(name);
    }
    @GetMapping("category/{category}")
    public List<Product> getProductByCategory(@PathVariable("category") String category,@RequestHeader(name = "API-Key", defaultValue = "123456") String apiKey){
        return productService.getProductByCategory(category);
    }
    @GetMapping("/price")
    public List<Product> getProductByRange(@RequestParam BigDecimal min, @RequestParam BigDecimal max,@RequestHeader(name = "API-Key", defaultValue = "123456") String apiKey){
        return productService.getProductByPriceRange(min,max);
    }
}
