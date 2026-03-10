package com.example.demo.services;

import com.example.demo.exception.ProductNotFoundException;
import com.example.demo.models.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private List<Product> products=new ArrayList<>();

    public void addProduct(Product product){
        products.add(product);

    }

    public List<Product> getAllProducts(){
        return products;
    }

    public Product getProductByName(String name){
        for(Product p: products){
            if(p.getName().equalsIgnoreCase(name)){
                return p;
            }
        }
        throw new ProductNotFoundException("Product not found: " + name);
    }

    public void updateProduct(String name, Product updated){
        for(Product p:products){
            if(p.getName().equalsIgnoreCase(name)){
                p.setName(updated.getName());
                p.setPrice(updated.getPrice());
                p.setCategory(updated.getCategory());
                p.setQuantity(updated.getQuantity());
            }
        }

    }
public void deleteProduct(String name){
        for(Product p: products){
            if(p.getName().equalsIgnoreCase(name)){
                products.remove(p);
            }
        }
}

public List<Product> getProductsByCategory(String category){
        List<Product> result=new ArrayList<>();
        for(Product p: products){
            if(p.getCategory().equalsIgnoreCase(category)){
                result.add(p);
            }
        }
        return result;
}

public List<Product> getProductsByPriceRange(double minPrice, double maxPrice){
        List<Product> range=new ArrayList<>();
        for(Product p: products){
            if(p.getPrice()>minPrice && p.getPrice()<maxPrice){
                range.add(p);
            }
        }
        return  range;
}



}
