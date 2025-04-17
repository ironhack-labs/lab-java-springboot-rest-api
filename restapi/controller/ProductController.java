package com.ironhack.restapi.controller;

import com.ironhack.restapi.dto.ProductMinimalRequest;
import com.ironhack.restapi.dto.UpdateNameDTO;
import com.ironhack.restapi.model.Product;
import com.ironhack.restapi.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Get all products (GET/products)
    @GetMapping
    public List<Product> getProduct() {
        return productService.getAllProducts();
    }

    // Get product by name (GET/products/{name})
    @GetMapping("/{name}")
    public Product getProductByName(@PathVariable String name) {
        return productService.getProductByName(name);
    }

    // Get product by category (GET /products/category/{category})
    @GetMapping("/category/{category}")
    public List<Product> getProductsByCategory(@PathVariable String category) {
        return productService.getProductsByCategory(category);
    }

    // Get product by price range (GET /products/price?min={min}&max={max})
    @GetMapping("/price")
    public List<Product> getProductsByPriceRange(@RequestParam double min, @RequestParam double max) {
        return productService.getProductsByPriceRange(min, max);
    }

    // Create new product (POST/products)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product addProduct(@RequestBody Product product){
        return productService.addProduct(product);
    }

    // Update product (PUT /products/{name})
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable int id, @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }

    // Update name
    @PatchMapping("/{id}/name")
    public Product updateProductName(@PathVariable int id, @RequestBody UpdateNameDTO updateNameDTO) {
        return productService.changeProductName(id, updateNameDTO);
    }

    // Delete name
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProductById(@PathVariable int id){
        productService.deleteProductById(id);
    }
}
