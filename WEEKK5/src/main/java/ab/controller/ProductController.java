package ab.controller;

import ab.exception.UnauthorizedException;
import ab.model.Product;
import ab.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private static final String VALID_API_KEY = "123456";
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    private void checkKey(String apiKey) {
        if (!VALID_API_KEY.equals(apiKey)) {
//            throw new UnauthorizedException("invalid API-Key");
        }
    }

    @PostMapping
    public ResponseEntity<Product> create(
            @RequestHeader("API-Key") String apiKey,
            @Valid @RequestBody Product product) {
        checkKey(apiKey);
        Product created = productService.add(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public ResponseEntity<List<Product>> all(@RequestHeader("API-Key") String apiKey) {
        checkKey(apiKey);
        return ResponseEntity.ok(productService.getAll());
    }

    @GetMapping("/{name}")
    public ResponseEntity<Product> byName(
            @RequestHeader("API-Key") String apiKey,
            @PathVariable String name) {
        checkKey(apiKey);
        return ResponseEntity.ok(productService.getByName(name));
    }

    @PutMapping("/{name}")
    public ResponseEntity<Product> update(
            @RequestHeader("API-Key") String apiKey,
            @PathVariable String name,
            @Valid @RequestBody Product product) {
        checkKey(apiKey);
        return ResponseEntity.ok(productService.update(name, product));
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Void> delete(
            @RequestHeader("API-Key") String apiKey,
            @PathVariable String name) {
        checkKey(apiKey);
        productService.delete(name);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Product>> byCategory(
            @RequestHeader("API-Key") String apiKey,
            @PathVariable String category) {
        checkKey(apiKey);
        return ResponseEntity.ok(productService.getByCategory(category));
    }

    @GetMapping("/price")
    public ResponseEntity<List<Product>> byPriceRange(
            @RequestHeader("API-Key") String apiKey,
            @RequestParam double min,
            @RequestParam double max) {
        checkKey(apiKey);
        return ResponseEntity.ok(productService.getByPriceRange(min, max));
    }
}
