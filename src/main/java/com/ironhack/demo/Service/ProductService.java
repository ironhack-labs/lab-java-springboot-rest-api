package com.ironhack.demo.Service;

import com.ironhack.demo.Model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class ProductService {
 private final  List<Product> products=new ArrayList<>();

 public Product addProduct(Product product){
     products.add(product);
     return product;
 }
public List<Product> getAllproducts(){
     return products;
 }
public Product getProductByName(String name){
return products.stream().filter(p -> p.getName().equalsIgnoreCase(name)).findFirst().orElse(null);

}
public Product productUpdate(Product product, String name){
     Product kohneproduct=getProductByName(name);
     if(product!=null){
         kohneproduct.setCatogery(product.getCatogery());
         kohneproduct.setName(product.getName());
         kohneproduct.setPrice(product.getPrice());
         kohneproduct.setQuanity(product.getQuanity());


     }
return kohneproduct;
    }
public void deleteProduct(String name){
    products.removeIf(p -> p.getName().equalsIgnoreCase(name));
}

public List<Product>getProductByCatogery(String catogery){
     return products.stream().filter(p -> p.getCatogery().equalsIgnoreCase(catogery))
             .collect(Collectors.toList());
}

    public List<Product> getProductsByPriceRange(double min, double max) {
        return products.stream()
                .filter(p -> p.getPrice() >= 0 && p.getPrice() <= 0)
                .collect(Collectors.toList());
    }
}



