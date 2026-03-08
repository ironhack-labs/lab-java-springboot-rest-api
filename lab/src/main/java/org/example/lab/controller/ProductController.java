package org.example.lab.controller;

import jakarta.validation.Valid;
import org.example.lab.model.Product;
import org.example.lab.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController{
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getProducts(){
        return productService.getAllProducts();
    }
    @PostMapping
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product){
        productService.addNewProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }
    @GetMapping("/{name}")
    public Product findProductByName(@PathVariable String name){
        return productService.getProductByName(name);
    }
    @PutMapping("/{name}")
    public void updateProduct(@PathVariable String name, @Valid @RequestBody Product product){
        productService.updateProduct(name, product);
    }
    @DeleteMapping("/{name}")
    public void deleteProduct(@PathVariable String name){
        productService.deleteProduct(name);
    }
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable String category) {
        List<Product> filtered = productService.getProductsByCategory(category);
        if (filtered.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(filtered);
    }
    @GetMapping("/price")
    public ResponseEntity<List<Product>> getProductsByPrice( @RequestParam double min,
                                                             @RequestParam double max){
        List<Product> filtered = productService.getProductsByPrice(min,max);
        if (filtered.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(filtered);
    }
}
