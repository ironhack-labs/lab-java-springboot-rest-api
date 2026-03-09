package services;
import models.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private List<Product> products = new ArrayList<>();

    public void createProduct(Product product){
        products.add(product);
    }

    public List<Product> getAllProducts(){
        return products;
    }

    public Product getProductByName(String name){

        for(Product p : products){
            if(p.getName().equalsIgnoreCase(name)){
                return p;
            }
        }

        return null;
    }

    public void updateProduct(String name, Product newProduct){

        for(Product p : products){

            if(p.getName().equalsIgnoreCase(name)){

                p.setName(newProduct.getName());
                p.setPrice(newProduct.getPrice());
                p.setCategory(newProduct.getCategory());
                p.setQuantity(newProduct.getQuantity());

                return;
            }
        }
    }

    public void deleteProduct(String name){

        Product product = getProductByName(name);

        if(product != null){
            products.remove(product);
        }
    }

    public List<Product> getProductsByCategory(String category){

        List<Product> result = new ArrayList<>();

        for(Product p : products){
            if(p.getCategory().equalsIgnoreCase(category)){
                result.add(p);
            }
        }

        return result;
    }

    public List<Product> getProductsByPrice(double min, double max){

        List<Product> result = new ArrayList<>();

        for(Product p : products){

            if(p.getPrice() >= min && p.getPrice() <= max){
                result.add(p);
            }
        }

        return result;
    }
}