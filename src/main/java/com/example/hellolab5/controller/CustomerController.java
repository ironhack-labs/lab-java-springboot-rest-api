package com.example.hellolab5.controller;

import com.example.hellolab5.Customer;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final List<Customer> customers = new ArrayList<>();

    @PostMapping
    public ResponseEntity<String> create(@Valid @RequestBody Customer c) {
        customers.add(c);
        return ResponseEntity.ok("Customer created");
    }

    @GetMapping
    public List<Customer> getAll() { return customers; }

    @GetMapping("/{email}")
    public Customer getByEmail(@PathVariable String email) {
        return customers.stream().filter(c -> c.getEmail().equals(email)).findFirst()
                .orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    @PutMapping("/{email}")
    public void update(@PathVariable String email, @Valid @RequestBody Customer updated) {
        getByEmail(email).setAddress(updated.getAddress());
        // Altri campi...
    }

    @DeleteMapping("/{email}")
    public void delete(@PathVariable String email) {
        customers.removeIf(c -> c.getEmail().equals(email));
    }

    @GetMapping("/")
    public String welcome() {
        return "API is running! Use /products or /customers endpoints.";
    }
}