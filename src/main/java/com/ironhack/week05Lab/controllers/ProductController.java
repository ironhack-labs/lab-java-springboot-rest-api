package com.ironhack.week05Lab.controllers;

import com.ironhack.week05Lab.models.Product;
import com.ironhack.week05Lab.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
Uses constructor injection for the ProductService
Requires an "API-Key" header for all requests (value: "123456")
Has the following endpoints:
POST /products - Create new product
GET /products - Get all products
GET /products/{name} - Get product by name
PUT /products/{name} - Update product
DELETE /products/{name} - Delete product
GET /products/category/{category} - Get products by category
GET /products/price?min={min}&max={max} - Get products by price range
 */
@RestController
@RequestMapping(path = "api/products")

public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    public Product addProduct(@RequestBody @Valid Product product) {
        return productService.addProduct(product);
    }

    @GetMapping()
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("{name}")
    public Object getProductByName(@PathVariable @Valid String name) {
        return productService.getProductByName(name);
    }

    @PutMapping("{name}")
    @ResponseStatus(code = HttpStatus.OK)
    public Product updateProduct(@PathVariable @Valid String name, @RequestBody @Valid Product product) {
        return productService.updateProduct(name, product);
    }

    @DeleteMapping("{name}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable @Valid String name) {
        productService.deleteProduct(name);
    }

    @GetMapping("category/{category}")
    public List<Product> getProductsByCategory(@PathVariable @Valid String category) {
        return productService.getProductsByCategory(category);
    }

    @GetMapping("price")
    public List<Product> getProductsByPriceRange(@RequestParam @Valid double min, @RequestParam @Valid double max) {
        return productService.getProductsByPriceRange(min, max);
    }
}
