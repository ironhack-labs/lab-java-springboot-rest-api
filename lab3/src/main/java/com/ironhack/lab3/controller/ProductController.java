package com.ironhack.lab3.controller;

import com.ironhack.lab3.model.Product;
import com.ironhack.lab3.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/api/products")
public class ProductController {
    private  final ProductService productService;  //Uses constructor injection for the ProductService

//    Requires an "API-Key" header for all requests (value: "123456")


    public ProductController(ProductService productService) {
        this.productService = productService;
    }

//    POST /products - Create new product
    @PostMapping
    public Product createNewProduct(@RequestHeader ("API-Key") String apiKey,
                                    @RequestBody Product product){
        if(!apiKey.equals("123456")){
            throw new RuntimeException("Invalid API Key");
        }
        return productService.create(product);
    }

//    GET /products - Get all products
    @GetMapping
    public List<Product> getAllProducts(@RequestHeader ("API-Key") String apiKey){
        if(!apiKey.equals("123456")){
            throw new RuntimeException("Invalid API Key");
        }
        return productService.findAll();
    }

//    GET /products/{name} - Get product by name
    @GetMapping ("/name")
    public List<Product> getProductsByName(@RequestHeader ("API-Key") String apiKey,
                                           @PathVariable String name,
                                           @Valid @RequestBody Product product){
        if(!apiKey.equals("123456")){
            throw new RuntimeException("Invalid API Key");
        }
        return productService.findByName(" searched name");
    }

//    PUT /products/{name} - Update product
    @PutMapping ("/name")
    public Product updateProduct (@RequestHeader ("API-Key") String apiKey,
                                  @PathVariable Long id,
                                  @Valid @RequestBody Product product){
        if(!apiKey.equals("123456")){
            throw new RuntimeException("Invalid API Key");
        }
        return productService.update(id, product);

    }

//    DELETE /products/{name} - Delete product
    @GetMapping ("/name")
    public ResponseEntity<Object> remove (@RequestHeader ("API-Key") String apiKey,
                                          @PathVariable Long id,
                                          @Valid @RequestBody Product product){
        if(!apiKey.equals("123456")){
            throw new RuntimeException("Invalid API Key");
        }
        productService.remove(id);
        return ResponseEntity.noContent().build();
    }

//    GET /products/category/{category} - Get products by category
    @GetMapping ("/category")
    public List<Product> getProductsByCategory (@RequestHeader ("API-Key") String apiKey,
                                                @PathVariable String category,
                                                @Valid @RequestBody Product product){
        if(!apiKey.equals("123456")){
            throw new RuntimeException("Invalid API Key");
        }
        return productService.findByCategory( "category of product");
    }

//    GET /products/price?min={min}&max={max} - Get products by price range
    @GetMapping ("/price")
    public List<Product> getProductsByPrice (@RequestHeader ("API-Key") String apiKey,
                                             @PathVariable double price,
                                             @Valid @RequestBody Product product){
        if(!apiKey.equals("123456")){
            throw new RuntimeException("Invalid API Key");
        }
        return productService.findByPriceRange(10,1200);
    }
}
