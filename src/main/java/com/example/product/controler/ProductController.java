package com.example.product.controler;

import com.example.product.exception.InvalidPriceRangeException;
import com.example.product.exception.MissingApiKeyException;
import com.example.product.exception.ProductNotFoundException;
import com.example.product.model.Product;
import com.example.product.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    private static final String VALID_API_KEY = "123456";

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // API-Key check helper
    private boolean isValidApiKey(String apiKey) {
        return !VALID_API_KEY.equals(apiKey);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> addProduct(
            @RequestHeader(value = "API-Key", required = false) String apiKey,
            @Valid @RequestBody Product product) {
        if (apiKey == null || !apiKey.equals("123456")) {
            throw new MissingApiKeyException("Missing or invalid API Key");
        }
        productService.addProduct(product);
        return ResponseEntity.ok("Product added successfully");
    }

    @GetMapping
    public ResponseEntity<?> getAllProducts(
            @RequestHeader(value = "API-Key", required = false) String apiKey) {
        if (apiKey == null || !apiKey.equals("123456")) {
            throw new MissingApiKeyException("Missing or invalid API Key");
        }
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{name}")
    public ResponseEntity<?> getProductByName(
            @RequestHeader(value = "API-Key", required = false) String apiKey,
            @PathVariable String name) {
        if (apiKey == null || !apiKey.equals("123456")) {
            throw new MissingApiKeyException("Missing or invalid API Key");
        }

        return ResponseEntity.ok(
                productService.getProductByName(name)
                        .orElseThrow(() -> new ProductNotFoundException("Product not found: " + name))
        );
    }

    @PutMapping("/{name}")
    public ResponseEntity<?> updateProduct(
            @RequestHeader(value = "API-Key", required = false) String apiKey,
            @PathVariable String name,
            @Valid @RequestBody Product product) {
        if (apiKey == null || !apiKey.equals("123456")) {
            throw new MissingApiKeyException("Missing or invalid API Key");
        }

        boolean updated = productService.updateProduct(name, product);
        return updated ? ResponseEntity.ok("Product updated") :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<?> deleteProduct(
            @RequestHeader(value = "API-Key", required = false) String apiKey,
            @PathVariable String name) {
        if (apiKey == null || !apiKey.equals("123456")) {
            throw new MissingApiKeyException("Missing or invalid API Key");
        }

        boolean deleted = productService.deleteProduct(name);
        return deleted ? ResponseEntity.ok("Product deleted") :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<?> getProductsByCategory(
            @RequestHeader(value = "API-Key", required = false) String apiKey,
            @PathVariable String category) {
        if (apiKey == null || !apiKey.equals("123456")) {
            throw new MissingApiKeyException("Missing or invalid API Key");
        }

        List<Product> products = productService.getProductsByCategory(category);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/price")
    public ResponseEntity<?> getProductsByPriceRange(
            @RequestHeader(value = "API-Key", required = false) String apiKey,
            @RequestParam double min,
            @RequestParam double max) {
        if (apiKey == null || !apiKey.equals("123456")) {
            throw new MissingApiKeyException("Missing or invalid API Key");
        }
        if (min > max) {
            throw new InvalidPriceRangeException("Minimum price cannot be greater than maximum price");
        }
        List<Product> products = productService.getProductsByPriceRange(min, max);
        return ResponseEntity.ok(products);
    }
//    // Constructor injection
//    public ProductController(ProductService productService) {
//        this.productService = productService;
//    }
//
//    // POST /products - Create new product
//    @PostMapping
//    public ResponseEntity<String> addProduct(@Valid @RequestBody Product product) {
//        productService.addProduct(product);
//        return ResponseEntity.ok("Product added successfully.");
//    }
//
//    // GET /products - Get all products
//    @GetMapping
//    public List<Product> getAllProducts() {
//        return productService.getAllProducts();
//    }
//
//    // GET /products/{name} - Get product by name
//    @GetMapping("/{name}")
//    public ResponseEntity<Product> getProductByName(@PathVariable String name) {
//        return productService.getProductByName(name)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }
//
//    // PUT /products/{name} - Update product
//    @PutMapping("/{name}")
//    public ResponseEntity<String> updateProduct(@PathVariable String name,
//                                                @Valid @RequestBody Product updatedProduct) {
//        boolean updated = productService.updateProduct(name, updatedProduct);
//        if (updated) {
//            return ResponseEntity.ok("Product updated successfully.");
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    // DELETE /products/{name} - Delete product
//    @DeleteMapping("/{name}")
//    public ResponseEntity<String> deleteProduct(@PathVariable String name) {
//        boolean deleted = productService.deleteProduct(name);
//        if (deleted) {
//            return ResponseEntity.ok("Product deleted successfully.");
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    // GET /products/category/{category} - Get products by category
//    @GetMapping("/category/{category}")
//    public List<Product> getProductsByCategory(@PathVariable String category) {
//        return productService.getProductsByCategory(category);
//    }
//
//    // GET /products/price?min={min}&max={max} - Get products by price range
//    @GetMapping("/price")
//    public List<Product> getProductsByPriceRange(@RequestParam double min,
//                                                 @RequestParam double max) {
//        return productService.getProductsByPriceRange(min, max);
//    }
}
