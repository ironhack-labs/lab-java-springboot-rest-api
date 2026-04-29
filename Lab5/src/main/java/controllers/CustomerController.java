package controllers;

import models.Customer;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    // Lista en memoria para simplificar
    private final List<Customer> customers = new ArrayList<>();

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@Valid @RequestBody Customer customer) {
        customers.add(customer);
    }

    @GetMapping
    public List<Customer> getAll() {
        return customers;
    }

    @GetMapping("/{email}")
    public Customer getByEmail(@PathVariable String email) {
        return customers.stream()
                .filter(c -> c.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    @PutMapping("/{email}")
    public void update(@PathVariable String email, @Valid @RequestBody Customer updatedCustomer) {
        Customer existing = getByEmail(email);
        customers.set(customers.indexOf(existing), updatedCustomer);
    }

    @DeleteMapping("/{email}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String email) {
        customers.remove(getByEmail(email));
    }
}