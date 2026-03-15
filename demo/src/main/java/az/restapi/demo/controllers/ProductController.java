package az.restapi.demo.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import az.restapi.demo.exceptions.UnauthorizedException;
import az.restapi.demo.models.Product;
import az.restapi.demo.security.ApiKeyAuthorization;
import az.restapi.demo.services.ProductService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")

public class ProductController {

    private ProductService productService;
    private ApiKeyAuthorization apiKeyAuthorization;

    public ProductController(ProductService productService, ApiKeyAuthorization apiKeyAuthorization) {
        this.productService = productService;
        this.apiKeyAuthorization = apiKeyAuthorization;
    }

    @PostMapping("/new-product")
    public void createNew(@RequestHeader("API-Key") String apiKey,
            @Valid @RequestBody Product product) {
        if (apiKeyAuthorization.isUnauthorized(apiKey)) {
            productService.addNewProduct(product);
        } else {
            throw new UnauthorizedException("Invalid API Key");
        }

    }

    @GetMapping("/show-all")
    public List<Product> getAll(@RequestHeader("API-Key") String apiKey) {
        if (apiKeyAuthorization.isUnauthorized(apiKey)) {
            return productService.getAllProducts();
        } else {
            throw new UnauthorizedException("Invalid API Key");
        }

    }

    @GetMapping("/{name}")
    public List<Product> getByName(@RequestHeader("API-Key") String apiKey,
            @PathVariable String name) {
        if (apiKeyAuthorization.isUnauthorized(apiKey)) {
            return productService.getProductsByName(name);
        } else {
            throw new UnauthorizedException("Invalid API Key");
        }

    }

    @PutMapping("/{productId}")
    public void updateQuantity(@RequestHeader("API-Key") String apiKey,
            @PathVariable int productId,
            @RequestBody int quantity) {
        if (apiKeyAuthorization.isUnauthorized(apiKey)) {
            productService.updateQuantity(productId, quantity);
        } else {
            throw new UnauthorizedException("Invalid API Key");
        }

    }

    @DeleteMapping("/{productId}")
    public void deleteProduct(@RequestHeader("API-Key") String apiKey,
            @PathVariable int productId) {
        if (apiKeyAuthorization.isUnauthorized(apiKey)) {
            productService.deleteProduct(productId);
        } else {
            throw new UnauthorizedException("Invalid API Key");
        }

    }

    @GetMapping("/category/{category}")
    public List<Product> getByCategory(@RequestHeader("API-Key") String apiKey,
            @PathVariable String category) {
        if (apiKeyAuthorization.isUnauthorized(apiKey)) {
            return productService.getByCategory(category);
        } else {
            throw new UnauthorizedException("Invalid API Key");
        }

    }

    @GetMapping("/price")
    public List<Product> getByPriceRange(@RequestHeader("API-Key") String apiKey,
            @RequestParam double min,
            @RequestParam double max) {
        if (apiKeyAuthorization.isUnauthorized(apiKey)) {
            return productService.getByPriceRange(min, max);
        } else {
            throw new UnauthorizedException("Invalid API Key");
        }

    }

}
