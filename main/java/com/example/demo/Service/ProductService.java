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
    public List<Product> getAllProducts(){
        return products;
    }
    public Optional<Product> getProductByName(String name){
        return products.stream().
                filter(p-> p.getName().equalsIgnoreCase(name)).findFirst();

    }
    public boolean updateProduct(String name, Product updatedProduct) {
        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(name)) {
                product.setName(updatedProduct.getName());
                product.setPrice(updatedProduct.getPrice());
                product.setCategory(updatedProduct.getCategory());
                product.setQuantity(updatedProduct.getQuantity());
                return true;
            }
        }
        return false;
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
