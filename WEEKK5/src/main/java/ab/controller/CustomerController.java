package ab.controller;

import ab.exception.UnauthorizedException;
import ab.model.Customer;
import ab.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private static final String VALID_API_KEY = "123456";
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // ✅ Méthode simple pour vérifier la clé API
    private void checkKey(String apiKey) {
        if (!Objects.equals(apiKey, VALID_API_KEY)) {
//            throw new UnauthorizedException("Invalid API-Key");
        }
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(
            @RequestHeader("API-Key") String apiKey,
            @Valid @RequestBody Customer customer) {

        checkKey(apiKey);
        Customer created = customerService.add(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers(
            @RequestHeader("API-Key") String apiKey) {

        checkKey(apiKey);
        return ResponseEntity.ok(customerService.getAll());
    }

    @GetMapping("/{email}")
    public ResponseEntity<Customer> getCustomerByEmail(
            @RequestHeader("API-Key") String apiKey,
            @PathVariable String email) {

        checkKey(apiKey);
        Customer c = customerService.getByEmail(email);
        if (c == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.ok(c);
    }

    @PutMapping("/{email}")
    public ResponseEntity<Customer> updateCustomer(
            @RequestHeader("API-Key") String apiKey,
            @PathVariable String email,
            @Valid @RequestBody Customer customer) {

        checkKey(apiKey);
        Customer updated = customerService.update(email, customer);
        if (updated == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deleteCustomer(
            @RequestHeader("API-Key") String apiKey,
            @PathVariable String email) {

        checkKey(apiKey);
        boolean deleted = customerService.delete(email);
        return deleted ? ResponseEntity.noContent().build()
                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
