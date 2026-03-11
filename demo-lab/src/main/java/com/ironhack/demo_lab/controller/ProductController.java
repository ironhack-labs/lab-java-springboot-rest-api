package com.ironhack.demo_lab.controller;


import com.ironhack.demo_lab.exception.InvalidApiKeyException;
import com.ironhack.demo_lab.models.Product;
import com.ironhack.demo_lab.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private static final String API_Key = "123456";

    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestHeader(value = "API-Key", required = false) String apiKey, @RequestBody @Valid Product product){
        if(apiKey == null || !apiKey.equals(API_Key)){
            throw new InvalidApiKeyException("API_KEY is wrong or missing!");
        }
        Product createdProduct = productService.create(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    @GetMapping
    public List<Product> getAllProducts(@RequestHeader(value = "API-Key", required = false) String apiKey){
        if(apiKey == null || !apiKey.equals(API_Key)){
            throw new InvalidApiKeyException("API_KEY is wrong or missing!");
        }
        return productService.findAll();
    }


    @GetMapping("/search")
    public List<Product> getProductByName(@RequestHeader(value = "API-Key", required = false) String apiKey, @RequestParam String name){
        if(apiKey == null || !apiKey.equals(API_Key)){
            throw new InvalidApiKeyException("API_KEY is wrong or missing!");
        }
        return productService.getProductByName(name);
    }

    @GetMapping("/{id}")
    public Product getProductByName(@RequestHeader(value = "API-Key", required = false) String apiKey, @PathVariable Long id){
        if(apiKey == null || !apiKey.equals(API_Key)){
            throw new InvalidApiKeyException("API_KEY is wrong or missing!");
        }
        return productService.findById(id);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@RequestHeader(value = "API-Key", required = false) String apiKey, @PathVariable Long id, @RequestBody Product product){
        if(apiKey == null || !apiKey.equals(API_Key)){
            throw new InvalidApiKeyException("API_KEY is wrong or missing!");
        }
        productService.findById(id);
        return productService.update(id, product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@RequestHeader(value = "API-Key", required = false) String apiKey, @PathVariable Long id){
        if(apiKey == null || !apiKey.equals(API_Key)){
            throw new InvalidApiKeyException("API_KEY is wrong or missing!");
        }
        productService.findById(id);
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/category/{category}")
    public List<Product> getProductByCategory(@RequestHeader(value = "API-Key", required = false) String apiKey, @PathVariable String category){
        if(apiKey == null || !apiKey.equals(API_Key)){
            throw new InvalidApiKeyException("API_KEY is wrong or missing!");
        }
        return productService.getProductByCategory(category);
    }

    @GetMapping("/price")
    public List<Product> getProductsByPriceRange(@RequestHeader(value = "API-Key", required = false) String apiKey, @RequestParam Double min, @RequestParam Double max){
        if(apiKey == null || !apiKey.equals(API_Key)){
            throw new InvalidApiKeyException("API_KEY is wrong or missing!");
        }
        return productService.getProductByPriceRange(min, max);
    }
}
