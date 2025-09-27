package com.ironhack.labjavaspringbootrestapisolutions.service;


import com.ironhack.labjavaspringbootrestapisolutions.exception.ProductException;
import com.ironhack.labjavaspringbootrestapisolutions.model.Product;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private final List<Product> productList = new ArrayList<>();

    @PostConstruct
    private void init(){
        productList.add(new Product("Bed",1200,"Furniture",8));
        productList.add(new Product("Laptop",2300,"Electronic Device",30));
        productList.add(new Product("Raincoat",150,"Clothing",15));
        productList.add(new Product("Sofa",3000,"Furniture",4));

    }

    // Methods
    //  Add a new product
    public Product addNewProduct(Product product){
        productList.add(product);
        return product;
    }

    //  Get all products
    public List<Product> getAllProducts(){
        return productList;
    }

    //  Get product by name
    public Product getProductByName(String name){
        for (Product product : productList){
            if (product.getName().equals(name)){
                return product;
            }
        }
        throw new ProductException(name);
    }

    //  Update product
    public Product updateProduct(String name, Product product) {
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getName().equals(name)) {
                productList.get(i).setName(product.getName());
                productList.get(i).setPrice(product.getPrice());
                productList.get(i).setCategory(product.getCategory());
                productList.get(i).setQuantity(product.getQuantity());
                return productList.get(i);
            }
        }
        throw new ProductException(name);
    }

    //  Delete product
    public void deleteProduct(String name){
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getName().equals(name)) {
                productList.remove(i);
                return;
            }
        }
    }

    //  Get productList by category
    public List<Product> getProductByCategory(String category){
        List<Product> results = new ArrayList<>();
        for (Product product : productList){
            if (product.getCategory().equals(category)){
                results.add(product);
            }
        }
        if (results.isEmpty()){
            throw new ProductException(category);
        }
        return results;
    }

    //  Get productList by price range
    public List<Product> getProductByPriceRange(double min,double max){
        List<Product> results = new ArrayList<>();
        for (Product product : productList){
            if (product.getPrice() >= min && product.getPrice() <= max){
                results.add(product);
            }
        }
        if (results.isEmpty()){
            throw new ProductException(min+"-"+max);
        }
        return results;
    }



}
