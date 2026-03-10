package controller;
import model.Product;
import service.ProductService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {
    private ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    private void validateKey(String apiKey){
        if(!"123456".equals(apiKey)){
            throw new RuntimeException("Invalid Api Key");
        }
    }

    @PostMapping("/products")
    public List<Product> createProducts(@RequestBody @Valid Product product,@Valid @RequestHeader("API-Key") String apiKey){
        validateKey(apiKey);
        productService.addProduct(product);
        return productService.getAllProduct();
    }


    @GetMapping("/products")
    public List<Product> displayProducts(@RequestHeader("API-Key") String apiKey) {
        validateKey(apiKey);
        return productService.getAllProduct();
    }

    @GetMapping("/products/{name}")
    public Product displayProductByName(@PathVariable String name,@RequestHeader("API-Key") String apiKey){
        validateKey(apiKey);
        return productService.getProductByName(name);
    }

    @PutMapping("/products/{name}")
    public List<Product> displayUpdateProducts(@PathVariable String name, @RequestBody @Valid Product updateInfo,@RequestHeader("API-Key") String apiKey){
         validateKey(apiKey);
        productService.updateProduct(name,updateInfo);
        return productService.getAllProduct();
    }

    @DeleteMapping("/products/{name}")
    public void displayDeleteProducts(@PathVariable String name,@RequestHeader("API-Key") String apiKey){
        validateKey(apiKey);
        productService.deleteProduct(name);
    }

    @GetMapping("/products/category/{category}")
    public List<Product> displayProductsByCategory(@PathVariable String category,@RequestHeader("API-Key") String apiKey){
        validateKey(apiKey);
        return productService.getProductByCategory(category);
    }

    @GetMapping("/products/price")
    public List<Product> displayProductsByPrice_Range(@RequestParam("min") double min_price, @RequestParam("max") double max_price, @RequestHeader("API-Key") String apiKey){
        validateKey(apiKey);
        return productService.getProductsByPriceRange(min_price, max_price);
    }
}
