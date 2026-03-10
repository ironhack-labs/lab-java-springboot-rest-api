package com.example.za.Controllers;

import com.example.za.Models.Product;
import com.example.za.Services.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;
    private static final String Api_Key="za";

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    private boolean isValidApiKey(String apiKey) {
        return Api_Key.equals(apiKey);
    }
    @PostMapping
    public ResponseEntity<?> createProduct(@RequestHeader("API-Key")String apikey, @Valid @RequestBody Product product )
    {
        if(!isValidApiKey(apikey)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("API key is invalid!");
        }
        productService.addProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }
    @GetMapping
    public ResponseEntity<?> getAllProducts(@RequestHeader("API-Key")String apikey){
        if(!isValidApiKey(apikey)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(("API key is invalid!"));

        }
        List<Product> products=productService.getAllProducts();
        return ResponseEntity.ok(products);
    }
    @GetMapping("/{name}")
    public ResponseEntity<Object> getProductsByName(@RequestHeader("API-Key") String apikey,@PathVariable String name){
        if(!isValidApiKey(apikey)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("API key is invalid!");
        }
        Optional<Product> product=productService.getProductByName(name);
        if(product.isEmpty()){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Requested body was not found!");
        }
        return  ResponseEntity.ok(product);
    }
    @PutMapping("/{name}")
    public ResponseEntity<?> updateProductByName(@RequestHeader("API-Key") String apiKey, @PathVariable String name, @Valid @RequestBody Product product) {
        if(!isValidApiKey(apiKey)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("API key is invalid!");
        }
        productService.updateProductByName(name, product);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<?> deleteProductByName(@RequestHeader("API-Key") String apiKey, @PathVariable String name) {
        if(!isValidApiKey(apiKey)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("API key is invalid!");
        }
        productService.deleteProductByName(name);
        return ResponseEntity.status((HttpStatus.NO_CONTENT)).body("Deleted product");
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<?> getProductByCategory(@RequestHeader("API-Key") String api_Key,@PathVariable String category){
        if(!isValidApiKey(api_Key)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("API key is invalid!");
        }
        List<Product> products=productService.getProductsByCategory(category);
        if(products==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Requested body was not found!");
        }
        return ResponseEntity.ok(products);
    }
    @GetMapping("/price")
    public ResponseEntity<?> getProductByPriceRange(@RequestHeader("API-Key") String api_Key,@RequestParam double min,@RequestParam  double max){
        if(!isValidApiKey(api_Key)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("API key is invalid!");
        }
        List<Product> products=productService.getProductsByPriceRange(min,max);
        if(products==null){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Requested body was not found!");
        }
        return ResponseEntity.ok(products);
    }
}