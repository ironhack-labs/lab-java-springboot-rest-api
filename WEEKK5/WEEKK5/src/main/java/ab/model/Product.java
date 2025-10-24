package ab.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor      // ✅ Constructeur vide requis par JPA/Jackson
@AllArgsConstructor     // ✅ Constructeur complet utile pour les tests
public class Product {

    @Id
    @NotBlank(message = "name is required")
    @Size(min = 3, message = "name must be at least 3 characters")
    private String name;

    @Positive(message = "price must be a positive number")
    private double price;

    @NotBlank(message = "category is required")
    private String category;

    @Positive(message = "quantity must be a positive number")
    private int quantity;
}
