/*
Create a ProductController class that:

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

package com.antoninrgb.labjavarestapilocal.controller;
import com.antoninrgb.labjavarestapilocal.model.Product;
import com.antoninrgb.labjavarestapilocal.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private static final String API_KEY = "123456";

    ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestHeader("API-Key") String apiKey, @Valid @RequestBody Product product) {
        if (!API_KEY.equals(apiKey)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Product addedProduct = productService.add(product);
        return ResponseEntity.status(HttpStatus.MULTI_STATUS).body(addedProduct);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getProduct(@RequestHeader("API-Key") String apiKey) {
        if (!API_KEY.equals(apiKey)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(productService.getProducts());
    }

    @GetMapping("/{name}")
    public ResponseEntity<Product> getByName(@RequestHeader("API-Key") String apiKey, @PathVariable String name) {
        if (!API_KEY.equals(apiKey)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(productService.getByName(name));
    }


    @GetMapping("/category/{category}")
    public ResponseEntity<List<Product>> getByCategory(@RequestHeader("API-Key") String apiKey, @PathVariable String category) {
        if (!API_KEY.equals(apiKey)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(productService.getByCategory(category));
    }

    @GetMapping("/price")
    public ResponseEntity<List<Product>> getByPriceRange(@RequestHeader("API-Key") String apiKey, @RequestParam double min, @RequestParam double max) {
        if (!API_KEY.equals(apiKey)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(productService.getByPriceRange(min, max));
    }

    @PutMapping("/{name}")
    public ResponseEntity<Product> updateProduct(@RequestHeader("API-Key") String apiKey, @Valid @RequestBody Product product, @PathVariable String name) {
        if (!API_KEY.equals(apiKey)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(productService.updateProduct(name, product));
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Boolean> deleteProduct(@RequestHeader("API-Key") String apiKey, @PathVariable String name) {
        if (!API_KEY.equals(apiKey)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(productService.deleteProduct(name));
    }


}
