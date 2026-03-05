package com.ironhack.demo.Controller;


import com.ironhack.demo.Model.Product;
import com.ironhack.demo.Service.ProductService;
import jakarta.validation.Valid;
import org.apache.catalina.mbeans.SparseUserDatabaseMBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/products ")
public class ProductController {
private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

@PostMapping
    public ResponseEntity<Product>creatProduct(@RequestHeader("API-Key") String apiKey,@Valid @RequestBody Product product){
        Product created=productService.addProduct(product);
return new  ResponseEntity <>(created, HttpStatus.CREATED);
    }
@GetMapping
public List<Product> getAllProducts(@RequestHeader("API-Key") String apiKey) {
    return productService.getAllproducts();}
    @GetMapping("/{name}")
    public Product GetProductByName( @RequestHeader("API-Key") String apiKey,@PathVariable  String name){
return productService.getProductByName(name);
}
@PutMapping("/{name}")
public ResponseEntity<Product> updateProduct(@RequestHeader("API-Key") String apiKey,@PathVariable String name,@RequestBody @Valid Product product){
Product uptadeProduct=productService.productUpdate(product, product.getName());
return ok(uptadeProduct) ;
}
@DeleteMapping("/{name}")
    public ResponseEntity<String>  ProductDeleter( @RequestHeader("API-Key") String apiKey,@PathVariable String name){
       productService.deleteProduct(name);
return  ResponseEntity.ok("product deleted succesfully");
    }
@GetMapping("/category/{category}")
    public ResponseEntity<List<Product>> getProductbyCategory( @RequestHeader("API-Key") String apiKey,@PathVariable String catogery){
       List<Product>products= productService.getProductByCatogery(catogery);
        return ResponseEntity.ok(products);

}
@GetMapping("/price")
    public ResponseEntity<List<Product>> getProductsByPriceRange( @RequestHeader("API-Key") String apiKey,@RequestParam  double a, @RequestParam double b ){
        List<Product>products=productService.getProductsByPriceRange(a, b);
return ResponseEntity.ok(products);
    }



    }
