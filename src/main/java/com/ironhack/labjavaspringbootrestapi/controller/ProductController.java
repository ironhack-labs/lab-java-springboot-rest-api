package com.ironhack.labjavaspringbootrestapi.controller;

import com.ironhack.labjavaspringbootrestapi.model.Product;
import com.ironhack.labjavaspringbootrestapi.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping
    public List<Product> getContact() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public Product getContactById(@PathVariable Long id) {
        return productService.findById(id);
    }

    @GetMapping("/search")
    public List<Product> getContacts(@RequestParam(required = false) String name) {
        if (name != null && !name.isBlank()) {
            return productService.findByName(name);
        }
        return productService.findAll();
    }

    @PutMapping("/{id}")
    public Product fullUpdateContact(@PathVariable Long id, @Valid @RequestBody Product product) {
        return productService.fullUpdate(id, product.getName(), product.getCategory(), product.getQuantity(), product.getPrice());
    }

    @PatchMapping("/{id}")
    public Product partialUpdateContact(@PathVariable Long id, @RequestBody Product product) {
        return productService.fullUpdate(id, product.getName(), product.getCategory(), product.getQuantity(), product.getPrice());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
