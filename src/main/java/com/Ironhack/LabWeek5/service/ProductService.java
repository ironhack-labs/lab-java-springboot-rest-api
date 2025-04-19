package com.Ironhack.LabWeek5.service;

import com.Ironhack.LabWeek5.model.Product;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

        private List<Product> productStorage ;
        @PostConstruct
        private void initStorage() {
                productStorage = new ArrayList<>(
                        List.of(
                                new Product("mar1", "Babbo-Lancher", 153.65,"ranged weapon",3),
                                new Product("mar2", "13-Totem-Rod", 123.21,"malee weapon",5),
                                new Product("mar3", "Spike & Hammer", 153.65,"cursed weapon",7)
                        )
                );
        }
        //get all
        public List<Product> getProduct() {
                return productStorage;
        }
        //get data by id
        public Product getProductById(String id){
                for (Product product : productStorage) {
                        if (product.getId().equals(id)) {
                                return product;
                        }
                }
                return null;
        }
        //get data by name
        public Product getProductByName(String name){
                for (Product product : productStorage) {
                        if (product.getName().equals(name)) {
                                return product;
                        }
                }
                return null;
        }
        //get data by category
        public Product getProductByCategory(String category){
                for (Product product : productStorage) {
                        if (product.getCategory().equals(category)) {
                                return product;
                        }
                }
                return null;
        }
        //add data
        public Product addProduct(Product product){
               Product productToAdd = new Product(product.getId(), product.getName(), product.getPrice(), product.getCategory(), product.getQuantity());
               productStorage.add(productToAdd);
               return productToAdd;
        }
        //del data
        public void delProductById(String id){
                for (int i = 0; i < productStorage.size(); i++) {
                        if (productStorage.get(i).getId().equals(id)) {
                                productStorage.remove(i);
                                break;
                        }
                }
        }
        //edit data
        public Product editProduct(String id, Product product) {
                if(product.getId() !=null && !product.getId().equals(id)){
                return null;
                }

        for(Product exisitingProduct : productStorage){
                if(exisitingProduct.getId().equals(id)){
                        exisitingProduct.setId(product.getId());
                        exisitingProduct.setName(product.getName());
                        exisitingProduct.setPrice(product.getPrice());
                        exisitingProduct.setCategory(product.getCategory());
                        exisitingProduct.setQuantity(product.getQuantity());
                        return exisitingProduct;
                }
        }
        return null;
        }


}