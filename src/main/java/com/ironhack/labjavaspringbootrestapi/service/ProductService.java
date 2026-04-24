package com.ironhack.labjavaspringbootrestapi.service;

import com.ironhack.labjavaspringbootrestapi.model.Product;
import org.springframework.stereotype.Service;
import com.ironhack.labjavaspringbootrestapi.exception.ProductNotFoundException;
import com.ironhack.labjavaspringbootrestapi.exception.InvalidPriceRangeException;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private final List<Product> products = new ArrayList<>();

    //Metodo para añadir un producto
    public Product addProduct(Product product){
        products.add(product);
        return product;
    }

    //Metodo para obtener todos los productos
    public List<Product> getAllProducts(){
        return products;
    }

    //Metodo obtener productos por nombre
    public Product getProductByName(String name){
        for(Product product : products){
            if(product.getName().equalsIgnoreCase(name)){
                return product;
            }
        }
        throw new ProductNotFoundException("Product not found");
    }

    //Metodo para actualizar un producto
    public Product updateProduct(String name, Product updatedProduct) {
        Product existingProduct = getProductByName(name);

        if (existingProduct != null) {
            existingProduct.setName(updatedProduct.getName());
            existingProduct.setPrice(updatedProduct.getPrice());
            existingProduct.setCategory(updatedProduct.getCategory());
            existingProduct.setQuantity(updatedProduct.getQuantity());
            return existingProduct;
        }

        return null;
    }
    //Metodo para eliminar un producto
    public void deleteProduct(String name){
        Product product = getProductByName(name);
        if(product != null){
            products.remove(product);
        }
    }

    // Metodo get para obtener productos por categoría
    public List<Product> getProductsByCategory(String category) {
        List<Product> productsByCategory = new ArrayList<>();

        for (Product product : products) {
            if (product.getCategory().equalsIgnoreCase(category)) {
                productsByCategory.add(product);
            }
        }

        return productsByCategory;
    }

    // Metodo get para obtener productos por rango de precios
    public List<Product> getProductsByPriceRange(double minPrice, double maxPrice) {
        if (minPrice > maxPrice) {
            throw new InvalidPriceRangeException("Min price cannot be greater than max price");
        }

        List<Product> productsByPriceRange = new ArrayList<>();

        for (Product product : products) {
            if (product.getPrice() >= minPrice && product.getPrice() <= maxPrice) {
                productsByPriceRange.add(product);
            }
        }

        return productsByPriceRange;
    }

}
