package com.Ironhack.LabWeek5.controller;

import com.Ironhack.LabWeek5.model.Product;
import com.Ironhack.LabWeek5.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;


    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    //show-time :)
    @GetMapping
    public List<Product> geProduct() {
        return productService.getProduct();
    }
    //mostra il contenuto di product in base all'id
    @GetMapping("/id/{id}")
    public Product getProductById(@PathVariable String id) {
        return productService.getProductById(id);
    }
    @GetMapping("/name/{name}")
    public Product getProductByName(@PathVariable String name) {
        return productService.getProductByName(name);
    }
    @GetMapping("/category/{category}")
    public Product getProductByCategory(@PathVariable String category) {
        return productService.getProductByCategory(category);
    }
    //add Product
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product addProduct(@RequestBody Product product){
        return productService.addProduct(product);
    }
    //del Product
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delProductById(@PathVariable String id){
        productService.delProductById(id);
    }
    //edit Product
    @PutMapping("/{id}")
    public Product editProduct(@PathVariable String id, @RequestBody Product product) {
    return productService.editProduct(id, product);
    }

}