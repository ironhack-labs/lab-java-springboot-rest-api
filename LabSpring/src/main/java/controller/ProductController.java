package controller;
import jakarta.validation.Valid;
import models.Product;
import org.springframework.web.bind.annotation.*;
import services.ProductService;

import java.util.List;

import static extension.CheckApiKey.checkApiKey;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }



    @PostMapping
    public void addProduct(@RequestHeader(value="API-Key", required=false) String apiKey,
                           @Valid @RequestBody Product product){

        checkApiKey(apiKey);
        productService.createProduct(product);
    }

    @GetMapping
    public List<Product> getAllProducts(@RequestHeader(value="API-Key", required=false) String apiKey){

        checkApiKey(apiKey);
        return productService.getAllProducts();
    }

    @GetMapping("/{name}")
    public Product getProduct(@RequestHeader(value="API-Key", required=false) String apiKey,
                              @PathVariable String name){

        checkApiKey(apiKey);
        return productService.getProductByName(name);
    }

    @PutMapping("/{name}")
    public void updateProduct(@RequestHeader(value="API-Key", required=false) String apiKey,
                              @PathVariable String name,
                              @Valid @RequestBody Product product){

        checkApiKey(apiKey);
        productService.updateProduct(name, product);
    }

    @DeleteMapping("/{name}")
    public void deleteProduct(@RequestHeader(value="API-Key", required=false) String apiKey,
                              @PathVariable String name){

        checkApiKey(apiKey);
        productService.deleteProduct(name);
    }

    @GetMapping("/category/{category}")
    public List<Product> getByCategory(@RequestHeader(value="API-Key", required=false) String apiKey,
                                       @PathVariable String category){

        checkApiKey(apiKey);
        return productService.getProductsByCategory(category);
    }

    @GetMapping("/price")
    public List<Product> getByPrice(@RequestHeader(value="API-Key", required=false) String apiKey,
                                    @RequestParam double min,
                                    @RequestParam double max){

        checkApiKey(apiKey);
        return productService.getProductsByPrice(min, max);
    }
}