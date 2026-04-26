package com.ironhack.lab_springboot_api.controller;

import com.ironhack.lab_springboot_api.model.Customer;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private List<Customer> customers = new ArrayList<>();

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer customer) {
        customers.add(customer);
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Customer> getAll() {
        return customers;
    }

    @GetMapping("/{email}")
    public ResponseEntity<Customer> getByEmail(@PathVariable String email) {
        for (Customer c : customers) {
            if (c.getEmail().equalsIgnoreCase(email)) {
                return ResponseEntity.ok(c);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{email}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable String email, @Valid @RequestBody Customer updated) {
        for (Customer c : customers) {
            if (c.getEmail().equalsIgnoreCase(email)) {
                c.setName(updated.getName());
                c.setAge(updated.getAge());
                c.setAddress(updated.getAddress());
                return ResponseEntity.ok(c);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable String email) {
        customers.removeIf(c -> c.getEmail().equalsIgnoreCase(email));
        return ResponseEntity.noContent().build();
    }
}