package com.example.Labs5.Controller;

import com.example.Labs5.Product.Product;
import com.example.Labs5.Service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private static final String VALID_API_KEY = "123456";

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("Add")
    public ResponseEntity<Void> addProduct(
            @RequestHeader("API-Key") String apiKey,
            @Valid @RequestBody Product product) {
        return validateApiKey(apiKey) ? createProduct(product) : forbiddenResponse();
    }

    @GetMapping("/All")
    public ResponseEntity<List<Product>> getAllProducts(@RequestHeader("API-Key") String apiKey) {
        return validateApiKey(apiKey) ? ResponseEntity.ok(productService.getAllProducts()) : forbiddenResponse();
    }

    @GetMapping("/{name}")
    public ResponseEntity<Product> getProductByName(
            @RequestHeader("API-Key") String apiKey,
            @PathVariable String name) {
        return validateApiKey(apiKey) ? productByNameResponse(name) : forbiddenResponse();
    }

    @PutMapping("/{name}")
    public ResponseEntity<Void> updateProduct(
            @RequestHeader("API-Key") String apiKey,
            @PathVariable String name,
            @Valid @RequestBody Product updatedProduct) {
        return validateApiKey(apiKey) ? updateProductResponse(name, updatedProduct) : forbiddenResponse();
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Void> deleteProduct(@RequestHeader("API-Key") String apiKey, @PathVariable String name) {
        return validateApiKey(apiKey) ? deleteProductResponse(name) : forbiddenResponse();
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Product>> getProductsByCategory(
            @RequestHeader("API-Key") String apiKey,
            @PathVariable String category) {
        return validateApiKey(apiKey) ? ResponseEntity.ok(productService.getProductsByCategory(category)) : forbiddenResponse();
    }

    @GetMapping("/price")
    public ResponseEntity<List<Product>> getProductsByPriceRange(
            @RequestHeader("API-Key") String apiKey,
            @RequestParam double min,
            @RequestParam double max) {
        return validateApiKey(apiKey) ? ResponseEntity.ok(productService.getProductsByPriceRange(min, max)) : forbiddenResponse();
    }

    private ResponseEntity<Void> createProduct(Product product) {
        productService.addProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    private ResponseEntity<Product> productByNameResponse(String name) {
        Product product = productService.getProductByName(name);
        return product != null ? ResponseEntity.ok(product) : ResponseEntity.notFound().build();
    }

    private ResponseEntity<Void> updateProductResponse(String name, Product updatedProduct) {
        productService.updateProduct(name, updatedProduct);
        return ResponseEntity.noContent().build();
    }

    private ResponseEntity<Void> deleteProductResponse(String name) {
        productService.deleteProduct(name);
        return ResponseEntity.noContent().build();
    }

    private static boolean validateApiKey(String apiKey) {
        return VALID_API_KEY.equals(apiKey);
    }

    private static <T> ResponseEntity<T> forbiddenResponse() {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }
}