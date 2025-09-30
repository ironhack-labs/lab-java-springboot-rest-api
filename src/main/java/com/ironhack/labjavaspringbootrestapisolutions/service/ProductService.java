package com.ironhack.labjavaspringbootrestapisolutions.service;

import com.ironhack.labjavaspringbootrestapisolutions.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class ProductService {
    public final ArrayList<Product> products = new ArrayList<>();

    public ProductService() {
        products.add(new Product("Laptop", 599.99, "Electronic", 1));
        products.add(new Product("T-shirt", 39.00, "Clothes", 4));
    }

    public ArrayList<Product> getProducts() {
        return products;
    }
    public ArrayList<Product> getAll(){
        return products;
    }
  public Product addProducts(Product product) {
        products.add(product);
        return product;

    }

    public ArrayList<Product> getByName(String name) {
        ArrayList <Product> productByName = new ArrayList<>();
        for (Product p : products) {
            if (p.getName() != null && p.getName().equals(name)) {
                productByName.add(p);
                return productByName;
            }
        }
        throw new RuntimeException("Name not found!");

    }
        public ArrayList<Product> getByCategory (String category){
        ArrayList<Product> productsByCat = new ArrayList<>();
        for (Product d : products){
            if (d.getCategory() !=null && d.getCategory().equals(category)){
                productsByCat.add(d);
                return productsByCat;
            }
        }
            throw new RuntimeException("Category not found");
        }
        public Product getByPriceRange(double min,double max){
        for (Product t : products){
            if (t.getPrice() !=0 && t.getPrice() >= min && t.getPrice() <= max){
                ;
            }
        }
        throw new RuntimeException("Invalid price range");
        }
        public Product update(String name,double newPrice, int newQuantity){
        for (Product updatedProduct : products){
            if (updatedProduct.getName() !=null && updatedProduct.getName().equals(name)){
                updatedProduct.setPrice(newPrice);
                updatedProduct.setQuantity(newQuantity);
                return updatedProduct;
            }
        }
throw new RuntimeException("Product not found");

        }
        public Product delete(String name){
        for (Product deleteProduct : products){
            if (deleteProduct.getName() !=null && deleteProduct.getName().equals(name)){
                products.remove(deleteProduct);
               return deleteProduct;
            }
        }
        throw new RuntimeException("Product not found");
        }
        }