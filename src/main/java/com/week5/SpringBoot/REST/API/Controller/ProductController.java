package com.week5.SpringBoot.REST.API.Controller;

import com.week5.SpringBoot.REST.API.Model.Product;
import com.week5.SpringBoot.REST.API.Service.ProductService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/products") // So müssen wir "/products" nicht überall hinschreiben

public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Hilfsmethode für den Key, um den Code oben sauber zu halten
    private void checkKey(String apiKey) {
        if (!"123456".equals(apiKey)) {
            throw new RuntimeException("Invalid API-Key!");
        }
    }

    @PostMapping
    public Product addProduct(@RequestHeader("API-Key") String key, @Valid @RequestBody Product product) {
        checkKey(key);
        Logger logger = Logger.getLogger("ProductAPI");
        logger.info("Adding a new product: " + product.getName());

        return productService.save(product);
    }

    @GetMapping
    public List<Product> getAll(@RequestHeader("API-Key") String key) {
        checkKey(key);
        Logger logger = Logger.getLogger("ProductAPI");
        logger.info("Loading all products");

        return productService.findAll();
    }

    @GetMapping("/{name}")
    public Product getByName(@RequestHeader("API-Key") String key, @PathVariable String name) {
        checkKey(key);
        Logger logger = Logger.getLogger("ProductAPI");
        logger.info("Searching for: " + name);

        return productService.findByName(name);
    }

    // Delete product
    @DeleteMapping("/{name}")
    public String deleteProduct(@RequestHeader("API-Key") String key, @PathVariable String name) {
        checkKey(key);
        Logger logger = Logger.getLogger("ProductAPI");
        logger.info("Deleting product: " + name);

        productService.deleteProduct(name);
        return "Product " + name + " deleted.";
    }

    // Get products by category
    @GetMapping("/category/{category}")
    public List<Product> getByCategory(@RequestHeader("API-Key") String key, @PathVariable String category) {
        checkKey(key);
        return productService.getProductByCategory(category);
    }

    // Get products by price range
    @GetMapping("/price")
    public List<Product> getByPrice(@RequestHeader("API-Key") String key, @RequestParam double min, @RequestParam double max) {
        checkKey(key);
        if (min > max) throw new RuntimeException("Invalid price range");
        return productService.getProductsByPriceRange(min, max);
    }


}