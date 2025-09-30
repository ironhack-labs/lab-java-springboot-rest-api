package com.ironhack.labjavaspringbootrestapisolution.controller;


import com.ironhack.labjavaspringbootrestapisolution.model.Product;
import com.ironhack.labjavaspringbootrestapisolution.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getProduct(@RequestHeader (value = "API-KEY") String apiKey) {
        if(apiKey == null || !apiKey.equals("123456")){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid API Key");
        }
        return productService.getProduct();
    }

    @GetMapping("/name/{name}")
    public Product getProductByName(@PathVariable String name) {
        return productService.getProductByName(name);
    }

    @GetMapping("/category/{category}")
    public Product getProductByCategory(@PathVariable String category) {
        return productService.getProductByCategory(category);
    }

    @GetMapping("/price")
    public List<Product> getProductByPriceRange(@RequestParam Double min, @RequestParam Double max) {
        return productService.getProductByPriceRange(min, max);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }


    @PutMapping("/{name}")
    public Product updateProductByName(@PathVariable String name,
                                       @RequestBody Product product) {
        return productService.updateProductByName(name, product);
    }

    @DeleteMapping("/{name}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProductByName(@PathVariable String name) {
        productService.deleteProductByName(name);
    }

}
