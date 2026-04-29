package controllers;

import models.Product;
import services.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    private void checkAuth(String key) {
        if (!"123456".equals(key)) throw new RuntimeException("Unauthorized");
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@Valid @RequestBody Product p, @RequestHeader("API-Key") String key) {
        checkAuth(key);
        productService.addProduct(p);
    }

    @GetMapping
    public List<Product> getAll(@RequestHeader("API-Key") String key) {
        checkAuth(key);
        return productService.getAll();
    }

    @GetMapping("/{name}")
    public Product getByName(@PathVariable String name, @RequestHeader("API-Key") String key) {
        checkAuth(key);
        return productService.getByName(name);
    }

    @PutMapping("/{name}")
    public void update(@PathVariable String name, @Valid @RequestBody Product p, @RequestHeader("API-Key") String key) {
        checkAuth(key);
        productService.update(name, p);
    }

    @DeleteMapping("/{name}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String name, @RequestHeader("API-Key") String key) {
        checkAuth(key);
        productService.delete(name);
    }

    @GetMapping("/category/{category}")
    public List<Product> getByCat(@PathVariable String category, @RequestHeader("API-Key") String key) {
        checkAuth(key);
        return productService.getByCategory(category);
    }

    @GetMapping("/price")
    public List<Product> getByPrice(@RequestParam double min, @RequestParam double max, @RequestHeader("API-Key") String key) {
        checkAuth(key);
        return productService.getByPrice(min, max);
    }
}