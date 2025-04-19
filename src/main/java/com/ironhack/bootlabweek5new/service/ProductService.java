package com.ironhack.bootlabweek5new.service;

import com.ironhack.bootlabweek5new.model.Product;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private List<Product> productsStorage ;


    @PostConstruct
    private void initStorage() {
        productsStorage = new ArrayList<>(
                List.of(
                        new Product("Product1", 15.40, "Toys", 500),
                        new Product("Product2", 105.18, "Aparel", 305),
                        new Product("Product3", 29.90, "Food", 203),

                        )
        );

    }




    //Get all products
    public List<Product> getProducts() {
        return productsStorage;
    }

    //Get product by name
    public Product getProductByName(String name){
        for (Product product : productsStorage) {
            if (product.getName().equals(name)) {
                return product;
            }
        }
        return null;
    }

    //Add a new product
    public Product addProduct(Product product){
        Product productToAdd = new Product(product.getName(), product.getPrice(),product.getCategory(), product.getQuantity());
        productsStorage.add(productToAdd);
        return productToAdd;
    }

    //Update product
    public Product updateProduct(String name, Product product) {
        if (product.getName() != null && product.getName() != name){
            return null;
        }
        for (Product exisitingProduct: productsStorage){
            if (exisitingProduct.getName() == name){
                exisitingProduct.setPrice(product.getPrice());
                exisitingProduct.setCategory(product.getCategory());
                exisitingProduct.setQuantity(product.getQuantity());

                return exisitingProduct;
            }
        }
        return null;
    }

    //Delete product
    public void deleteProductByName(String name){
        for (int i = 0; i < productsStorage.size(); i++) {
            if (productsStorage.get(i).getName().equals(name)) {
                productsStorage.remove(i);
                break;
            }
        }
    }

    //Get products by category
        public Product getProductsByCategory(String category){
        for (Product product : productsStorage) {
            if (product.getCategory().equals(category)) {
                return product;
            }
        }
        return null;
    }


    //Get products by price range
    public List<Product> getProductsByPriceRange(double min, double max) {
        return productsStorage.stream()
                .filter(product -> product.getPrice() >= min && product.getPrice() <= max)
                .collect(Collectors.toList());
    }
}







}
