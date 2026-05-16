package com.ironhack.labjavaspringbootrestapi.controller;

import com.ironhack.labjavaspringbootrestapi.model.Product;
import com.ironhack.labjavaspringbootrestapi.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    //Constructor
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    //Primer EndPoint CREAR PRODCUTO
    @PostMapping
    public String createProduct(@Valid @RequestBody Product product,
                                @RequestHeader("API-key")String apiKey){

        if(!apiKey.equals("123456")){
            return "Invalid API Key";
        }
        productService.addProduct(product);
        return "Product created successfully";
    }

    // Obtener todos los productos
    @GetMapping
    public List<Product> getAllProducts(
            @RequestHeader("API-Key") String apiKey){

        if(!apiKey.equals("123456")){
            return null;
        }

        return productService.getAllProducts();
    }


    // Buscar producto por nombre
    @GetMapping("/{name}")
    public Product getProductByName(
            @PathVariable String name,
            @RequestHeader("API-Key") String apiKey){

        if(!apiKey.equals("123456")){
            return null;
        }

        return productService.getProductByName(name);
    }


    // Actualizar producto
    @PutMapping("/{name}")
    public String updateProduct(
            @PathVariable String name,
            @Valid@RequestBody Product product,
            @RequestHeader("API-Key") String apiKey){

        if(!apiKey.equals("123456")){
            return "Invalid API Key";
        }

        productService.updateProduct(name, product);

        return "Product updated";
    }


    // Eliminar producto
    @DeleteMapping("/{name}")
    public String deleteProduct(
            @PathVariable String name,
            @RequestHeader("API-Key") String apiKey){

        if(!apiKey.equals("123456")){
            return "Invalid API Key";
        }

        productService.deleteProduct(name);

        return "Product deleted";
    }


    // Buscar por categoría
    @GetMapping("/category/{category}")
    public List<Product> getProductsByCategory(
            @PathVariable String category,
            @RequestHeader("API-Key") String apiKey){

        if(!apiKey.equals("123456")){
            return null;
        }

        return productService.getProductsByCategory(category);
    }


    // Buscar por rango de precio
    @GetMapping("/price")
    public List<Product> getProductsByPriceRange(
            @RequestParam Double min,
            @RequestParam Double max,
            @RequestHeader("API-Key") String apiKey) {

        if (!apiKey.equals("123456")) {
            return null;
        }

        return productService.getProductsByPriceRange(min, max);

    }

}
