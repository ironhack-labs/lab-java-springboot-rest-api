package com.ironhack.labjavaspringbootrestapisolutions.controllers;

import com.ironhack.labjavaspringbootrestapisolutions.model.Product;
import com.ironhack.labjavaspringbootrestapisolutions.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping()
public class ProductController {

    // Uses constructor injection for the ProductService
    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    // Requires an "API-Key" header for all requests (value: "123456")   ???


    // POST /products - Create new product
    @PostMapping("/products")
    @ResponseStatus(code=HttpStatus.CREATED)
    public Product addNewProduct(@RequestBody @Valid Product product){
        return productService.addNewProduct(product);
    }

    // GET /products - Get all products
    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    // GET /products/{name} - Get product by name
    @GetMapping("/products/{name}")
    public Product getProductByName(@PathVariable String name){
        return productService.getProductByName(name);
    }

    // PUT /products/{name} - Update product
    @PutMapping("/products/{name}")
    public Product updateProduct(@PathVariable String name, @RequestBody @Valid Product product){
        return productService.updateProduct(name, product);
    }

    // DELETE /products/{name} - Delete product
    @DeleteMapping("/products/{name}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable String name){
        productService.deleteProduct(name);
    }

    // GET /products/category/{category} - Get products by category
    @GetMapping("/products/category/{category}")
    public List<Product> getProductByCategory(@PathVariable String category){
        return productService.getProductByCategory(category);
    }

    // GET /products/price?min={min}&max={max} - Get products by price range
    @GetMapping("/products/price")
    public List<Product> getProductByPriceRange(@RequestParam double min,@RequestParam double max){
        return productService.getProductByPriceRange(min, max);
    }

    // @ResponseStatus








}
