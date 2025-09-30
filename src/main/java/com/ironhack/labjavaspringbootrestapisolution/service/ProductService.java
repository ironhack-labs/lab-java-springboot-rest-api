package com.ironhack.labjavaspringbootrestapisolution.service;

import com.ironhack.labjavaspringbootrestapisolution.exception.Exception;
import com.ironhack.labjavaspringbootrestapisolution.model.Product;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private List<Product> productList;

    @PostConstruct
    private void init(){
        productList = new ArrayList<>(List.of(
                new Product("phone", 20.0, "electronics", 10),
                new Product("lip-gloss", 30.0, "makeup", 12),
                new Product("shirt", 15.0, "clothing", 10),
                new Product("monopoly", 25.0, "games", 15)
        )
        );
    }

    public List<Product> getProduct() {
        return productList;
    }

    public Product getProductByName(String name) {
        for (Product product : productList) {
            if (product.getName().equals(name)) {
                return product;
            } else throw new Exception.ProductNotFoundException("Product with name: " + name + " not found");
        }
        return null;
    }

    public Product getProductByCategory(String category) {
        for (Product product : productList) {
            if (product.getCategory().equals(category)) {
                return product;
            } else throw new Exception.ProductNotFoundException("Product with Category: " + category + " not found");
        }
        return null;
    }

    public List <Product> getProductByPriceRange(Double min , Double max) {

        if (min < 0 && max > 100){
            throw new Exception.InvalidPriceRangeException("Price range is invalid");

        }
        return productList.stream()
                .filter(p -> p.getPrice() >= min && p.getPrice() <= max)
                .collect(Collectors.toList());


    }

    public Product createProduct(Product product) {
        productList.add(product);
        return product;
    }


    public void deleteProductByName(String name) {
        for (int i = 0; i< productList.size(); i++){
            if(productList.get(i).getName().equals(name)){
                productList.remove(i);
                return;
            }
        }
    }

    public Product updateProductByName(String name, Product product) {
        for (Product value : productList) {
            if (value.getName().equals(name)) {
                value.setName(product.getName());
                return value;
            }
        }
        return null;
    }




}
