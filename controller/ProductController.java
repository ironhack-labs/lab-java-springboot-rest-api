package com.ironhack.labweek5.controller;

import com.ironhack.labweek5.model.Product;
import com.ironhack.labweek5.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

//Uses constructor injection for the ProductService
//Requires an "API-Key" header for all requests (value: "123456")
//Has the following endpoints:

@RestController
//@RequestMapping("/products")
public class ProductController{
    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    //helper method to check the API Key
    public void checkApiKey(String apiKeyValue){
        if(!"123456".equals(apiKeyValue)){
            throw new RuntimeException("Invalid API Key");
        }
    }

    //GET /products - Get all products
    @GetMapping("/products")
    public ArrayList<Product> getAllProducts(
            @RequestHeader("API-Key") String apiKeyValue
    ){
        checkApiKey(apiKeyValue);
        return productService.getAllProducts();
    }

    //GET /products/{name} - Get product by name
    @GetMapping("/products/{name}")
    public Product getProductByName(
            @RequestHeader("API-Key") String apiKeyValue,
            @PathVariable String name
    ){
        checkApiKey(apiKeyValue);
        return productService.getProductByName(name);
    }

    //GET /products/category/{category} - Get products by category
    @GetMapping("/products/category/{category}")
    public List<Product> getProductsByCategory(
            @RequestHeader("API-Key") String apiKeyValue,
            @PathVariable String category
    ){
        checkApiKey(apiKeyValue);
        return productService.getProductsByCategory(category);
    }

    //GET /products/price?min={min}&max={max} - Get products by price range
    @GetMapping("/products/price")
    public List<Product> getProductsByPriceRange(
            @RequestHeader("API-Key") String apiKeyValue,
            @RequestParam double min,
            @RequestParam double max
    ){
        checkApiKey(apiKeyValue);
        return productService.getProductsByPriceRange(min, max);
    }

    //POST /products - Create new product
    @PostMapping("/products")
//    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<Product> create(
            @RequestHeader("API-Key") String apiKeyValue,
            @Valid @RequestBody Product product
    ){
        checkApiKey(apiKeyValue);

        Product created = productService.create(product);

        //building URI
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{name}")
                .buildAndExpand(created.getName())
                .toUri();

        return ResponseEntity.created(location).body(created);
    }


    //PUT /products/{name} - Update product
    @PutMapping("/products/{name}")
    public Product update(
            @RequestHeader("API-Key") String apiKeyValue,
            @PathVariable String name,
            @Valid @RequestBody Product product
    ){
        checkApiKey(apiKeyValue);

        return productService.update(name, product);
    }

    //DELETE /products/{name} - Delete product
    @DeleteMapping("/products/{name}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @RequestHeader("API-Key") String apiKeyValue,
            @PathVariable String name
    ){
        checkApiKey(apiKeyValue);
        productService.delete(name);
    }




}