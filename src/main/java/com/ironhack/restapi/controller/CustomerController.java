package com.ironhack.restapi.controller;


import com.ironhack.restapi.model.Customer;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final List<Customer> customers = new ArrayList<>();

    // Create new customer
    @PostMapping
    public ResponseEntity<String> addCustomer(@Valid @RequestBody Customer customer) {
        customers.add(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body("Customer created successfully");
    }

    // Get all customers
    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(customers);
    }

    // Get customer by email
    @GetMapping("/{email}")
    public ResponseEntity<Customer> getCustomerByEmail(@PathVariable String email) {
        Optional<Customer> customer = customers.stream()
                .filter(c -> c.getEmail().equalsIgnoreCase(email))
                .findFirst();
        return customer.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update customer
    @PutMapping("/{email}")
    public ResponseEntity<String> updateCustomer(@PathVariable String email, @Valid @RequestBody Customer updated) {
        Optional<Customer> customerOpt = customers.stream()
                .filter(c -> c.getEmail().equalsIgnoreCase(email))
                .findFirst();

        if (customerOpt.isPresent()) {
            Customer existing = customerOpt.get();
            existing.setName(updated.getName());
            existing.setAge(updated.getAge());
            existing.setAddress(updated.getAddress());
            return ResponseEntity.ok("Customer updated successfully");
        }
        return ResponseEntity.notFound().build();
    }

    // Delete customer
    @DeleteMapping("/{email}")
    public ResponseEntity<String> deleteCustomer(@PathVariable String email) {
        boolean removed = customers.removeIf(c -> c.getEmail().equalsIgnoreCase(email));
        return removed ? ResponseEntity.ok("Customer deleted successfully")
                : ResponseEntity.notFound().build();
    }
}
