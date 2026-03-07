package org.ironhack.collections.lab.Service;

import org.ironhack.collections.lab.Exception.InvalidPriceRangeException;
import org.ironhack.collections.lab.Exception.ProductNotFoundException;
import org.ironhack.collections.lab.Model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private List<Product> products = new ArrayList<>();

    public Product addProduct(Product product) {
        products.add(product);
        return product;
    }

    public List<Product> getAllProducts() {
        return products;
    }
    public Product getProductByName(String name) {
        return products.stream().filter(p->p.getName().equalsIgnoreCase(name)).findFirst().orElseThrow(() -> new ProductNotFoundException("Product not found"));
    }

    public void updateProduct(String name, Product updated) {
        Product product = getProductByName(name);

        product.setName(updated.getName());
        product.setPrice(updated.getPrice());
        product.setCategory(updated.getCategory());
        product.setQuantity(updated.getQuantity());
    }
    public void deleteProduct(String name) {
        Product product = getProductByName(name);
        products.remove(product);
    }

    public List<Product> getProductsByCategory(String category) {
        return products.stream().filter(p->p.getCategory().equalsIgnoreCase(category)).collect(Collectors.toList());
    }

    public List<Product> getProductsByPriceRange(double min, double max) {
        if(min>max) {
            throw new InvalidPriceRangeException("Invalid price range");
        }
       return products.stream().filter(p->p.getPrice()>=min && p.getPrice()<=max).collect(Collectors.toList());
    }
}
