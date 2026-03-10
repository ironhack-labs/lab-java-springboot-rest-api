package com.example.demo.controllers;

import com.example.demo.models.Product;
import com.example.demo.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    public ProductService service;
    public ProductController(ProductService service){
        this.service=service;
    }
private void checkApiKey(String apiKey){
        if(apiKey==null || !apiKey.equals("123456")){
            throw new RuntimeException("Missing or invalid api key");
        }
}

 @PostMapping
    public void addProduct(@RequestHeader(value="API-Key", required = false) String apiKey, @Valid @RequestBody Product product){
        checkApiKey(apiKey);
        service.addProduct(product);
 }

 @GetMapping
    public List<Product> getProducts(@RequestHeader(value = "API-Key", required = false) String apiKey){
        checkApiKey(apiKey);
        return service.getAllProducts();
 }


 @GetMapping("/{name}")
    public Product getProductByName(@RequestHeader(value = "API-Key", required = false) String apiKey, @PathVariable String name){
        checkApiKey(apiKey);
      return   service.getProductByName(name);
 }

    @PutMapping("/{name}")
    public void updateProduct(@RequestHeader(value = "API-Key", required = false) String apiKey,
                              @PathVariable String name,
                              @RequestBody Product product) {

        checkApiKey(apiKey);
        service.updateProduct(name, product);
    }
    @DeleteMapping("/{name}")
    public void deleteProduct(@RequestHeader(value = "API-Key", required = false) String apiKey,
                              @PathVariable String name) {

        checkApiKey(apiKey);
        service.deleteProduct(name);
    }

    @GetMapping("/category/{category}")
    public List<Product> getByCategory(@RequestHeader(value = "API-Key", required = false) String apiKey,
                                       @PathVariable String category) {

        checkApiKey(apiKey);
        return service.getProductsByCategory(category);
    }

    @GetMapping("/price")
    public List<Product> getByPriceRange(@RequestHeader(value = "API-Key", required = false) String apiKey,
                                         @RequestParam double min,
                                         @RequestParam double max) {

        checkApiKey(apiKey);

        if (min > max) {
            throw new IllegalArgumentException("Invalid price range");
        }

        return service.getProductsByPriceRange(min, max);
    }
}
