package com.SpringRestAPI.Services;

import com.SpringRestAPI.Models.Product;
import com.SpringRestAPI.Models.ProductCategories;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {


    private List<Product> productList;

    public ProductService() {
        setProductList();
    }

    public void setProductList (){
        productList = new ArrayList<>();

        productList.add(new Product("iPhone 17",879.00, ProductCategories.ELECTRONIC,5));
        productList.add(new Product("Hot Wheels Porsche",4.99, ProductCategories.TOYS,20));
        productList.add(new Product("Ado's Best Adobum",39.00, ProductCategories.MUSIC,3));
        productList.add(new Product("Howl's Moving Castle",27.50, ProductCategories.MOVIE,50));
        productList.add(new Product("The Hunger Games",19.90, ProductCategories.BOOKS,110));
    }

    // add a new product
    public Product addNewProduct(String productName, double productPrice, ProductCategories productCategory, int productQuantity) {
        Product newProduct = new Product(productName, productPrice, productCategory, productQuantity);
        productList.add(newProduct);
        return newProduct;
    }

    // get all products
    public List<Product> getProductList() {
        return productList;
    }

    // get products by name
    public Optional<Product> getProductByName(String productName) {
        for (Product product : productList) {
            if (product.getProductName().equalsIgnoreCase(productName)) {
                return Optional.of(product); // return if product found with the name in that list
            }
        }
        return Optional.empty(); // if no product found by the name, it will return empty
    }

    // update product
    public Product updateProductName(String currentName, String newName) {
        for (Product product : productList) {
            if (product.getProductName().equalsIgnoreCase(currentName)) {
                product.setProductName(newName);
                return product;
            }
        }
        throw new RuntimeException("Product not found");
    }

    // delete product
    public Optional<Product> deleteProduct(String productName) {
        Optional<Product> found = productList.stream()
                .filter(p -> p.getProductName().equals(productName))
                .findFirst();

        found.ifPresent(productList::remove);
        return found;
    }

    // get products by category
    public List<Product> getProductByCategory (ProductCategories productCategory){
        List<Product> products = new ArrayList<>();
        for (Product product : productList) {
            if (product.getProductCategory().equals(productCategory)) {
                products.add(product);
            }
        }
        return products;
    }

    // get products by price range
    public List<Product> getProductsByPriceRange (double min, double max){
        List<Product> products = new ArrayList<>();
        for (Product product : productList) {
            if (product.getProductPrice()>=min&&product.getProductPrice()<=max) {
                products.add(product);
            }
        }
        return products;
    }

    /// Product → “There is definitely a product”
    /// Optional<Product> → “There might be a product”
    /// List<Product> → “There could be many products”

}
