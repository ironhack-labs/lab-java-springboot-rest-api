package com.ironhack.labjavaspringbootrestapi.controller;

import com.ironhack.labjavaspringbootrestapi.model.Product;
import com.ironhack.labjavaspringbootrestapi.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/products")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.findAll();
    }

    @GetMapping("/{name}")
    public List<Product> getProduct(@RequestParam(required = false) String name) {
        List<Product> products = productService.findAll();

        if(name != null && !name.isBlank()) {
            List<Product> filtered = new ArrayList<>();
            String lowerName = name.toLowerCase();
            for (Product p: products) {
                if (p.getName().toLowerCase().contains(lowerName)) {
                    filtered.add(p);
                }
            }
            products = filtered;
        }

        return products;
    }
}
