package com.ironhack.bootlabweek5new.controller;

import com.ironhack.bootlabweek5new.model.Product;
import com.ironhack.bootlabweek5new.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getProducts();
    }


    @GetMapping("/product/name")
    public Product getProductByName(@PathVariable("name") String productName) {
        return productService.getProductByName(productName);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @PutMapping("/product/updatename")
    public Product updateProduct(@PathVariable("name") String productName, @RequestBody Product product) {
        return productService.updateProduct(productName, product);
    }

     @DeleteMapping("/product/deletename")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProductByName(@PathVariable("name") String productName) {
        productService.deleteProductByName(productName);
    }

    @GetMapping("/product/category")
    public Product getProductsByCategory(@PathVariable("category") String productCategory) {
        return productService.getProductsByCategory(productCategory);
    }

    @GetMapping("/product/price")
    public Product getProductsByPriceRange(@PathVariable("price") double priceMin, @PathVariable("price") double priceMax) {
        return (Product) productService.getProductsByPriceRange(priceMin, priceMax);
    }




}



