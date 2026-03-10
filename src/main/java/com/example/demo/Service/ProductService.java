package com.example.demo.Service;

import com.example.demo.Model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private  List<Product> products=new ArrayList<>();

    public List<Product> addProduct(Product product){
        products.add(product);
        return products;
    }
    public Product findByName(String name){
        for(Product product:products){
            if(product.getName().equalsIgnoreCase(name)){
                return product;
            }
        }
        throw new RuntimeException("Book was not found");
    }
    public List<Product> getAllProducts(){
        return products;
    }
    public Optional<Product> getProductByName(String name){
        return products.stream().
                filter(p-> p.getName().equalsIgnoreCase(name)).findFirst();

    }
    public Product updateProduct(String name, Product updatedProduct) {
        Product existingProduct=findByName(name);
        existingProduct.setName(updatedProduct.getName());
        existingProduct.setCategory(updatedProduct.getCategory());
        existingProduct.setPrice(updatedProduct.getPrice());
        existingProduct.setQuantity(updatedProduct.getQuantity());
        return existingProduct;
    }
    public boolean deleteProduct(String name){
        return products.removeIf(p->p.getName().equalsIgnoreCase(name));
    }
    public List<Product> getProductsByCategory(String category){
        return products.stream().filter(p->p.getCategory().equalsIgnoreCase(category)).toList();
    }
    public List<Product> getProductsByPriceRange(double minPrice,double maxPrice){
        return products.stream().filter(p->p.getPrice()>minPrice &&p.getPrice()<maxPrice).toList();
    }


}
