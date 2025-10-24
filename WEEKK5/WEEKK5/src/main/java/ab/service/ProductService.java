package ab.service;

import ab.exception.InvalidPriceRangeException;
import ab.exception.ProductNotFoundException;
import ab.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class ProductService {

    private final List<Product> products = new ArrayList<>();

    // Ajouter un produit
    public Product add(Product p) {
        products.add(p);
        return p;
    }

    // Tous les produits
    public List<Product> getAll() {
        return products;
    }

    // Trouver par nom (ignore la casse)
    public Product getByName(String name) {
        for (Product p : products) {
            if (p.getName() != null && p.getName().equalsIgnoreCase(name)) {
                return p;
            }
        }
        throw new ProductNotFoundException("product not found: " + name);
    }

    // Mettre à jour par nom (remplace tous les champs)
    public Product update(String name, Product update) {
        Product existing = getByName(name);
        existing.setName(update.getName());
        existing.setPrice(update.getPrice());
        existing.setCategory(update.getCategory());
        existing.setQuantity(update.getQuantity());
        return existing;
    }

    // Supprimer par nom
    public void delete(String name) {
        Product existing = getByName(name);
        products.remove(existing);
    }

    // Produits par catégorie (ignore la casse)
    public List<Product> getByCategory(String category) {
        List<Product> result = new ArrayList<>();
        String target = category == null ? "" : category.toLowerCase(Locale.ROOT);
        for (Product p : products) {
            if (p.getCategory() != null &&
                    p.getCategory().toLowerCase(Locale.ROOT).equals(target)) {
                result.add(p);
            }
        }
        return result;
    }

    // Produits par intervalle de prix [min, max]
    public List<Product> getByPriceRange(double min, double max) {
        if (min < 0 || max < 0 || min > max) {
            throw new InvalidPriceRangeException(
                    "invalid price range: min <= max and both >= 0 required");
        }
        List<Product> result = new ArrayList<>();
        for (Product p : products) {
            double price = p.getPrice();
            if (price >= min && price <= max) {
                result.add(p);
            }
        }
        return result;
    }
}
