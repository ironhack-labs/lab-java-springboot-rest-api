/*
Create a ProductService class that manages a List of Products and has methods to:

Add a new product
Get all products
Get product by name
Update product
Delete product
Get products by category
Get products by price range
 */

package com.antoninrgb.labjavarestapilocal.service;
import com.antoninrgb.labjavarestapilocal.model.Product;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    // creating this list as a mock database
    private List<Product> productList = new ArrayList<>(List.of(
            new Product("Stella del mattino", 5.50, "Arma fisica", 5),
            new Product("Staffe della Doppia Fiamma", 45.00, "Arma elementale", 2),
            new Product("Arco del Tramontana", 15.50, "Arma elementale", 5)
    ));

    public Product add(Product product) {
        productList.add(product); // Mock save to database
        return product;
    }

    public List<Product> getProducts() {
        return productList;
    }

    public Product getByName(String name) {
        for (Product product : productList) {
            if (product.getName().equalsIgnoreCase(name)) {
                return product;
            }
        }
        return null;
    }

    public List<Product> getByCategory(String category) {
        List<Product> newListCat = new ArrayList<>();
        for (Product product : productList) {
            if (product.getCategory().equalsIgnoreCase(category)) {
                newListCat.add(product);
            }
        }
        if (!newListCat.isEmpty()) {
            return newListCat;
        } else {
            return null;
        }
    }

    public List<Product> getByPriceRange(double min, double max) {
        List<Product> newListPrice = new ArrayList<>();
        for (Product product : productList) {
            if (min < product.getPrice() && product.getPrice() < max) {
                newListPrice.add(product);
            }
        }
        if (!newListPrice.isEmpty()) {
            return newListPrice;
        } else {
            return null;
        }
    }

    public Product updateProduct(String name, Product product) {
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getName().equalsIgnoreCase(name)) {
                productList.set(i, product);
                return product;
            }
        }
        return null;
    }

    public boolean deleteProduct(String name) {
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getName().equalsIgnoreCase(name)) {
                productList.remove(i);
                return true;
            }
        }
        return false;
    }

}
