package com.ironhack.restapi.service;

import com.ironhack.restapi.dto.UpdateNameDTO;
import com.ironhack.restapi.model.Product;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private List<Product> productsList;

    @PostConstruct
    private void init(){

        productsList = new ArrayList<>();
        productsList.addAll(
                List.of(
                        new Product(1, "Chair", 139.99, "Furniture", 10),
                        new Product(2, "Table", 189.99, "Furniture", 15),
                        new Product(3, "Kettle", 49.99, "Appliances", 10),
                        new Product(4, "Persian carpet", 1399.99, "Carpets", 5)
                )
        );

        System.out.println("Products List Initialized");
    }

    // Add new product
    public Product addProduct(Product product) {
        productsList.add(product);
        return product;
    }

    // Get all products
    public List<Product> getAllProducts() {
        return new ArrayList<>(productsList);
    }

    // Get product by name
    public Product getProductByName(String name){
        for (Product product : productsList) {
            if (product.getName().equals(name)) {
                return product;
            }
        }
        return null;
    }

    // Update product
    public Product updateProduct(int id, Product product) {
        if (product.getId() != id){
            return null;
        }

        for (Product exisitingProduct: productsList){
            if (exisitingProduct.getId() == id){
                exisitingProduct.setName(product.getName());
                exisitingProduct.setPrice(product.getPrice());
                exisitingProduct.setCategory(product.getCategory());
                exisitingProduct.setQuantity(product.getQuantity());

                return exisitingProduct;
            }
        }
        return null;
    }

    // Delete product
    public void deleteProductById(int id){
        for (int i = 0; i < productsList.size(); i++) {
            if (productsList.get(i).getId() == id) {
                productsList.remove(i);
                break;
            }
        }
    }

    // Get product by category
    public List<Product> getProductsByCategory(String category){
        return productsList.stream()
                .filter(product -> product.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    // Get products by price range
    public List<Product> getProductsByPriceRange(double minPrice, double maxPrice) {
        return productsList.stream()
                .filter(p -> p.getPrice() >= minPrice && p.getPrice() <= maxPrice)
                .collect(Collectors.toList());
    }

    // Change product name
    public Product changeProductName(int id, UpdateNameDTO updateNameDTO) {
        for(Product product : productsList){
            if(product.getId() == id){
                product.setName(updateNameDTO.getNewName());
                return product;
            }
        }
        return null;
    }
}