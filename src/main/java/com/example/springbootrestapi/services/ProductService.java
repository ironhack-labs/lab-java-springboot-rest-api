package com.example.springbootrestapi.services;

import com.example.springbootrestapi.models.Product;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    private List<Product> productStorage;
    @PostConstruct
    public void init() {
        productStorage = new ArrayList<>(
                List.of(
                        new Product("Coffee",
                                new BigDecimal(5),
                                100,
                                "Drink"),
                        new Product("Coke",
                                new BigDecimal(3),
                                100,
                                "Drink")
                )
        );
    }
    public List<Product> addProduct(Product product){
        productStorage.add(product);
        return productStorage;
    }

    public List<Product> getAllProduct(){
        return productStorage;
    }

    public Product getProductsByName(String name){
        for(Product productRetriever: productStorage){
            if(productRetriever.getName().equals(name)){
                return productRetriever;
            }
        }
        return null;
    }

    public Product updateProduct(String name, Product product) {
        for(Product productRetriever : productStorage){
            if(productRetriever.getName().equals(name)){
                productRetriever.setPrice(product.getPrice());
                productRetriever.setCategory(product.getCategory());
                productRetriever.setQuantity(product.getQuantity());
                return productRetriever;
            }

        }
        return null;
    }

    public List<Product> deleteProduct(String name){
        for(Product productRetriever : productStorage){
            if(productRetriever.getName().equals(name)){
                productStorage.remove(productRetriever);
                return productStorage;
            }
        }
        return null;
    }

    public List<Product> getProductByCategory(String category){
        List<Product> productsByCat = new ArrayList<>();
        for(Product productRetriever : productStorage){
            if(productRetriever.getCategory().equals(category)){

                productsByCat.add(productRetriever);
            }
        }
        return productsByCat;
    }

    public List<Product> getProductByPriceRange(BigDecimal min, BigDecimal max){
        List<Product> productsByPrice = new ArrayList<>();
        for(Product productRetriever : productStorage){
            if(productRetriever.getPrice().compareTo(min) > 0 && productRetriever.getPrice().compareTo(max) < 0){
                productsByPrice.add(productRetriever);
            }
        }
        return productsByPrice;
    }
}
