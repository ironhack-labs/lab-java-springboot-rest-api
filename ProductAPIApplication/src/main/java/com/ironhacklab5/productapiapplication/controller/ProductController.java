package com.ironhacklab5.productapiapplication.controller;

import com.ironhacklab5.productapiapplication.exception.InvalidApiKeyException;
import com.ironhacklab5.productapiapplication.model.Product;
import com.ironhacklab5.productapiapplication.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller exposing all product-related endpoints.
 *
 * Every endpoint requires a valid "API-Key" header (value: "123456").
 * The service is injected via the constructor — no @Autowired needed.
 *
 * Base URL: /products
 */
@RestController
@RequestMapping("/products")
public class ProductController {

    // The expected value of the API-Key header
    private static final String VALID_API_KEY = "123456";

    // Injected via constructor (constructor injection — best practice)
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Validates the API-Key header.
     * Called at the start of every endpoint method.
     *
     * @param apiKey the value of the API-Key header
     * @throws InvalidApiKeyException if the key does not match
     */
    private void validateApiKey(String apiKey) {
        if (!VALID_API_KEY.equals(apiKey)) {
            throw new InvalidApiKeyException();
        }
    }

    // -----------------------------------------------------------------------
    // POST /products — create a new product
    // -----------------------------------------------------------------------

    /**
     * Creates a new product.
     * @Valid triggers validation annotations on the Product class.
     * Returns 201 Created on success.
     */
    @PostMapping
    public ResponseEntity<Product> createProduct(
            @RequestHeader("API-Key") String apiKey,
            @Valid @RequestBody Product product) {

        validateApiKey(apiKey);
        Product saved = productService.addProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // -----------------------------------------------------------------------
    // GET /products — get all products
    // -----------------------------------------------------------------------

    /**
     * Returns all products in the store.
     * Returns 200 OK with the list (empty list if no products exist).
     */
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(
            @RequestHeader("API-Key") String apiKey) {

        validateApiKey(apiKey);
        return ResponseEntity.ok(productService.getAllProducts());
    }

    // -----------------------------------------------------------------------
    // GET /products/category/{category} — must come BEFORE /products/{name}
    // so Spring does not treat "category" as a product name
    // -----------------------------------------------------------------------

    /**
     * Returns all products in the given category.
     * Returns 200 OK with a (possibly empty) list.
     */
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Product>> getProductsByCategory(
            @RequestHeader("API-Key") String apiKey,
            @PathVariable String category) {

        validateApiKey(apiKey);
        return ResponseEntity.ok(productService.getProductsByCategory(category));
    }

    // -----------------------------------------------------------------------
    // GET /products/price?min={min}&max={max} — price range filter
    // -----------------------------------------------------------------------

    /**
     * Returns products whose price falls within [min, max].
     * Returns 400 Bad Request if min > max.
     */
    @GetMapping("/price")
    public ResponseEntity<List<Product>> getProductsByPriceRange(
            @RequestHeader("API-Key") String apiKey,
            @RequestParam double min,
            @RequestParam double max) {

        validateApiKey(apiKey);
        return ResponseEntity.ok(productService.getProductsByPriceRange(min, max));
    }

    // -----------------------------------------------------------------------
    // GET /products/{name} — get single product by name
    // -----------------------------------------------------------------------

    /**
     * Returns a single product by name.
     * Returns 404 Not Found if the product does not exist.
     */
    @GetMapping("/{name}")
    public ResponseEntity<Product> getProductByName(
            @RequestHeader("API-Key") String apiKey,
            @PathVariable String name) {

        validateApiKey(apiKey);
        return ResponseEntity.ok(productService.getProductByName(name));
    }

    // -----------------------------------------------------------------------
    // PUT /products/{name} — update existing product
    // -----------------------------------------------------------------------

    /**
     * Updates an existing product identified by name.
     * Returns 404 Not Found if the product does not exist.
     */
    @PutMapping("/{name}")
    public ResponseEntity<Product> updateProduct(
            @RequestHeader("API-Key") String apiKey,
            @PathVariable String name,
            @Valid @RequestBody Product updatedProduct) {

        validateApiKey(apiKey);
        return ResponseEntity.ok(productService.updateProduct(name, updatedProduct));
    }

    // -----------------------------------------------------------------------
    // DELETE /products/{name} — delete product by name
    // -----------------------------------------------------------------------

    /**
     * Deletes a product by name.
     * Returns 204 No Content on success, 404 Not Found if product does not exist.
     */
    @DeleteMapping("/{name}")
    public ResponseEntity<Void> deleteProduct(
            @RequestHeader("API-Key") String apiKey,
            @PathVariable String name) {

        validateApiKey(apiKey);
        productService.deleteProduct(name);
        return ResponseEntity.noContent().build();
    }
}