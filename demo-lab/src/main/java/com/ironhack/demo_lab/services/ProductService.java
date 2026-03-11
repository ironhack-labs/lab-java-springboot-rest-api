package com.ironhack.demo_lab.services;

import com.ironhack.demo_lab.exception.ResourceNotFoundException;
import com.ironhack.demo_lab.models.Product;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class ProductService {
    private Long nextId = 1L;

    Map<Long , Product> products = new HashMap<>();

    public ProductService(){
        Product product1 = new Product("Bread", 9.99, "Flavor", 10);
        Product product2 = new Product("HP", 999.99, "Laptop", 5);
        product1.setId(nextId++);
        product2.setId(nextId++);
        products.put(product1.getId(), product1);
        products.put(product2.getId(), product2);
    }

    public Product findById(Long id){
        Product product = products.get(id);
        if(product == null){
            throw new ResourceNotFoundException("Product", id);
        }
        return product;
    }

    public List<Product> findAll(){
        return products.values().stream().toList();
    }

    public Product create(Product product){
        Product createdProduct = new Product();
        createdProduct.setName(product.getName());
        createdProduct.setQuantity(product.getQuantity());
        createdProduct.setCategory(product.getCategory());
        createdProduct.setPrice(product.getPrice());
        createdProduct.setId(nextId++);
        products.put(createdProduct.getId(), createdProduct);
        return createdProduct;
    }

    public List<Product> getProductByName(String name){
        return products.values().stream().filter(c ->
                c.getName().toLowerCase().contains(name.toLowerCase())).toList();
    }

    public List<Product> getProductByCategory(String category){
        return products.values().stream().filter(p ->
                p.getCategory().toLowerCase().contains(category.toLowerCase())).toList();
    }

    public List<Product> getProductByPriceRange(Double minPrice, Double maxPrice){
        if(minPrice < 0){
            throw new IllegalArgumentException("Minimum price cannot be negative!");
        }
        if (maxPrice > Integer.MAX_VALUE){
            throw new IllegalArgumentException("Maximum price cannot be larger than Integer limit!");
        }
        return products.values().stream().filter(p -> Double.compare(p.getPrice(), minPrice) == 1 && Double.compare(p.getPrice(), maxPrice) == -1).toList();
    }

    public Product update(Long id, Product product){
        Product foundProduct = findById(id);
        foundProduct.setName(product.getName());
        foundProduct.setPrice(product.getPrice());
        foundProduct.setQuantity(product.getQuantity());
        foundProduct.setCategory(product.getCategory());
        return foundProduct;
    }

    public void delete(Long id){
        findById(id);
        products.remove(id);
    }
}
