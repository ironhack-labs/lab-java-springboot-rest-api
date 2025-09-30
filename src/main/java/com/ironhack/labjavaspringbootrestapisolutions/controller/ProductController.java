package com.ironhack.labjavaspringbootrestapisolutions.controller;

import com.ironhack.labjavaspringbootrestapisolutions.model.Product;
import com.ironhack.labjavaspringbootrestapisolutions.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
@PostMapping
@ResponseStatus(HttpStatus.CREATED)
public Product addProduct(@RequestBody Product product, @RequestHeader("API-key") String apiKey){
        return productService.addProducts(product);
}

@GetMapping(("/all"))
    public ArrayList<Product> getAllProducts(@RequestHeader("API-key")String apiKey)
{
        return productService.getAll();
    }
@GetMapping("/{name}")
    public ArrayList<Product> getByName(@PathVariable String name, @RequestHeader("API-key") String apiKey){
    return productService.getByName(name);
    }
    @PutMapping("/{name}")
    public Product updateProduct(@PathVariable String name,@RequestBody Product updatedProduct,
                                 @RequestHeader("API-key") String apiKey){
        return productService.update(name, updatedProduct.getPrice(), updatedProduct.getQuantity());
    }

    @DeleteMapping("/{name}")
    public Product deleteProduct(@PathVariable String name,@RequestHeader("API-key")
    String apiKey){
        return productService.delete(name);
    }
@GetMapping("/category/{category}")
public ArrayList<Product> getByCategory(@PathVariable String category, @RequestHeader("API-key") String apiKey){
    return productService.getByCategory(category);
    }
    @GetMapping("/price")
    public Product getByPrice (@RequestParam double min,@RequestParam double max, @RequestHeader("API-key") String apiKey){
        return productService.getByPriceRange(min, max);
    }
}
