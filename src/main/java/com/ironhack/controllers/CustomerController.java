package com.ironhack.controllers;

import com.ironhack.models.Customer;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customers")
public class CustomerController {


    private final List<Customer> customers = new ArrayList<>();

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Customer createCustomer(@Valid @RequestBody Customer customer) {
        customers.add(customer);
        return customer;
    }

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customers;
    }

    @GetMapping("/{email}")
    public Customer getCustomerByEmail(@PathVariable String email) {
        Optional<Customer> customer = customers.stream()
                .filter(c -> c.getEmail().equalsIgnoreCase(email))
                .findFirst();

        if (customer.isEmpty()) {
            throw new RuntimeException("Customer not found");
        }
        return customer.get();
    }

    @PutMapping("/{email}")
    public Customer updateCustomer(@PathVariable String email, @Valid @RequestBody Customer updatedCustomer) {
        Optional<Customer> existing = customers.stream()
                .filter(c -> c.getEmail().equalsIgnoreCase(email))
                .findFirst();

        if (existing.isEmpty()) {
            throw new RuntimeException("Customer not found");
        }

        Customer c = existing.get();
        c.setName(updatedCustomer.getName());
        c.setAge(updatedCustomer.getAge());
        c.setAddress(updatedCustomer.getAddress());
        return c;
    }

    @DeleteMapping("/{email}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable String email) {
        boolean deleted = customers.removeIf(c -> c.getEmail().equalsIgnoreCase(email));
        if (!deleted) {
            throw new RuntimeException("Customer not found");
        }
    }
}