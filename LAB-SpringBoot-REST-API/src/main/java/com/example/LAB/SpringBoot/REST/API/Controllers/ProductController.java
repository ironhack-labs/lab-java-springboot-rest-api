package com.example.LAB.SpringBoot.REST.API.Controllers;

import com.example.LAB.SpringBoot.REST.API.Models.Product;
import com.example.LAB.SpringBoot.REST.API.Views.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private static final String API_KEY = "123456";

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    private boolean isApiKeyValid(String key) {
        return API_KEY.equals(key);
    }

//    Uses constructor injection for the ProductService
//    Requires an "API-Key" header for all requests (value: "123456")
//    Has the following endpoints:
//    POST /products - Create new product
//    GET /products - Get all products
//    GET /products/{name} - Get product by name
//    PUT /products/{name} - Update product
//    DELETE /products/{name} - Delete product
//    GET /products/category/{category} - Get products by category
//    GET /products/price?min={min}&max={max} - Get products by price range
@PostMapping
public ResponseEntity<?> createProduct(
        @RequestHeader(value = "API-Key") String key,
        @Valid @RequestBody Product product) {

    return ResponseEntity.ok(productService.addProduct(product));
}

    // GET /products - Get all products
    @GetMapping
    public ResponseEntity<?> getAllProducts(
            @RequestHeader(value = "API-Key") String key) {

        return ResponseEntity.ok(productService.getAllProducts());
    }

    // GET /products/{name} - Get product by name
    @GetMapping("/{name}")
    public ResponseEntity<?> getProduct(
            @RequestHeader(value = "API-Key") String key,
            @PathVariable String name) {

        Product product = productService.getProductByName(name);
        return (product != null) ? ResponseEntity.ok(product) : ResponseEntity.notFound().build();
    }

    // PUT /products/{name} - Update product
    @PutMapping("/{name}")
    public ResponseEntity<?> updateProduct(
            @RequestHeader(value = "API-Key") String key,
            @PathVariable String name,
            @Valid @RequestBody Product product) {

        Product updated = productService.updateProduct(name, product);
        return (updated != null) ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    // DELETE /products/{name} - Delete product
    @DeleteMapping("/{name}")
    public ResponseEntity<?> deleteProduct(
            @RequestHeader(value = "API-Key") String key,
            @PathVariable String name) {

        return productService.deleteProduct(name)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    // GET /products/category/{category} - Get products by category
    @GetMapping("/category/{category}")
    public ResponseEntity<?> getByCategory(
            @RequestHeader(value = "API-Key") String key,
            @PathVariable String category) {

        return ResponseEntity.ok(productService.getByCategory(category));
    }

    // GET /products/price?min={min}&max={max} - Get products by price range
    @GetMapping("/price")
    public ResponseEntity<?> getByPriceRange(
            @RequestHeader(value = "API-Key") String key,
            @RequestParam double min,
            @RequestParam double max) {

        return ResponseEntity.ok(productService.getByPriceRange(min, max));
    }
}
