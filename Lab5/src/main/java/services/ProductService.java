package services;

import models.Product;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class ProductService {
    private final List<Product> products = new ArrayList<>();

    public void addProduct(Product product) { products.add(product); }
    public List<Product> getAll() { return products; }

    public Product getByName(String name) {
        return products.stream()
                .filter(p -> p.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Product not found"));
    }

    public void update(String name, Product updated) {
        Product existing = getByName(name);
        products.set(products.indexOf(existing), updated);
    }

    public void delete(String name) {
        products.remove(getByName(name));
    }

    public List<Product> getByCategory(String cat) {
        return products.stream().filter(p -> p.getCategory().equalsIgnoreCase(cat)).toList();
    }

    public List<Product> getByPrice(double min, double max) {
        if (min > max) throw new IllegalArgumentException("Min price cannot be greater than max");
        return products.stream().filter(p -> p.getPrice() >= min && p.getPrice() <= max).toList();
    }
}