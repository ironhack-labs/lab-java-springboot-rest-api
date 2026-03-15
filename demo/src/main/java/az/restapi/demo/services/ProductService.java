package az.restapi.demo.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import az.restapi.demo.exceptions.InvalidPriceRangeException;
import az.restapi.demo.exceptions.ProductNotFoundException;
import az.restapi.demo.models.Product;

@Service
public class ProductService {

    private Map<Integer, Product> products = new HashMap<>();

    public void addNewProduct(Product newProduct) {

        products.put(newProduct.getProductId(), newProduct);
    }

    public List<Product> getAllProducts() {
        return new ArrayList<Product>(products.values());
    }

    // there is maybe many products with same name
    public List<Product> getProductsByName(String name) {

        List<Product> result = new ArrayList<>();

        for (Product product : products.values()) {
            if (product.getName().equals(name)) {
                result.add(product);
            }

        }
        if (result.isEmpty()) {
            throw new ProductNotFoundException("No product with name " + name);
        }
        return result;

    }

    public void updateQuantity(int id, int quantity) {

        Product target = products.get(id);

        target.setQuantity(quantity);

    }

    public void deleteProduct(int id) {

        Product removed = products.remove(id);
        if (removed == null) {
            throw new ProductNotFoundException("Product with id " + id + " not found");
        }

        products.remove(id);

    }

    public List<Product> getByCategory(String category) {

        List<Product> result = new ArrayList<>();

        for (Product product : products.values()) {

            if (product.getCategory().equals(category)) {

                result.add(product);
            }

        }

        return result;
    }

    public List<Product> getByPriceRange(double min, double max) {

        if(min>max){throw new InvalidPriceRangeException("invalid price range");}
        List<Product> result = new ArrayList<>();

        for (Product product : products.values()) {

            if (product.getPrice() <= max && product.getPrice() >= min) {
                result.add(product);
            }

        }

        return result;

    }

    public Map<Integer, Product> getProducts() {
        return products;
    }

    public void setProducts(Map<Integer, Product> products) {
        this.products = products;
    }

}
