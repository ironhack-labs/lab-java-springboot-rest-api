package com.ironhack.labjavaspringbootrestapi.service;

import com.ironhack.labjavaspringbootrestapi.model.Product;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private List<Product> products = new ArrayList<>();

    //Añadir producto Nuevo
    public void addProduct(Product product) {
        products.add(product);
    }

    //Obetener todos los Productos
    public List<Product> getAllProducts() {
        return products;
    }

    //Buscar Prodcuto por nombre
    public Product getProductByName(String name) {

        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(name)) {
                return product;
            }
        }

        return null;
    }

    //Actualizar producto
    public void updateProduct(String name, Product updatedProduct) {

        Product product = getProductByName(name);

        if (product != null) {
            product.setName(updatedProduct.getName());
            product.setPrice(updatedProduct.getPrice());
            product.setCategory(updatedProduct.getCategory());
            product.setQuantity(updatedProduct.getQuantity());
        }
    }

    //Eliminar Producto
    public void deleteProduct(String name) {

        Product product = getProductByName(name);
        if (product != null) {
            products.remove(product);
        }
    }

    //Buscar Prodcutos por categoriA
    public List<Product> getProductsByCategory(String category) {

        List<Product> filteredProducts = new ArrayList<>();

        for (Product product : products) {

            if (product.getCategory().equalsIgnoreCase(category)) {
                filteredProducts.add(product);
            }
        }
        return filteredProducts;
    }

    //Buscar Prdcutos Pror rango de precio
    public List<Product> getProductsByPriceRange(Double min, Double max) {

        List<Product> filteredProducts = new ArrayList<>();

        for (Product product : products) {
            if (product.getPrice() >= min &&
                    product.getPrice() <= max) {

                filteredProducts.add(product);
            }
        }
        return filteredProducts;

    }
}
