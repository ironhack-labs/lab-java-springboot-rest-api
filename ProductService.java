package week5;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class ProductService {

	private final List<Product> products = new ArrayList<>();

	public Product addProduct(Product product) {
		products.add(product);
		return product;
	}

	public List<Product> getAllProducts() {
		return new ArrayList<>(products);
	}

	public Optional<Product> getProductByName(String name) {
		return products.stream()
				.filter(product -> product.getName().equalsIgnoreCase(name))
				.findFirst();
	}

	public Optional<Product> updateProduct(String name, Product updatedProduct) {
		Optional<Product> existingProduct = getProductByName(name);

		existingProduct.ifPresent(product -> {
			product.setName(updatedProduct.getName());
			product.setPrice(updatedProduct.getPrice());
			product.setCategory(updatedProduct.getCategory());
			product.setQuantity(updatedProduct.getQuantity());
		});

		return existingProduct;
	}

	public boolean deleteProduct(String name) {
		return products.removeIf(product -> product.getName().equalsIgnoreCase(name));
	}

	public List<Product> getProductsByCategory(String category) {
		return products.stream()
				.filter(product -> product.getCategory().equalsIgnoreCase(category))
				.toList();
	}

	public List<Product> getProductsByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
		return products.stream()
				.filter(product -> product.getPrice().compareTo(minPrice) >= 0)
				.filter(product -> product.getPrice().compareTo(maxPrice) <= 0)
				.toList();
	}
}
