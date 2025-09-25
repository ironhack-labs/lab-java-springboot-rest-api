package com.springboot_lab.controller;

import com.springboot_lab.exception.ProductNotFoundException;
import com.springboot_lab.model.Customer;
import com.springboot_lab.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    // Constructor injection
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer customer) {
        Customer createdCustomer = customerService.addCustomer(customer);
        return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/{email}")
    public ResponseEntity<Customer> getCustomerByEmail(@PathVariable String email) {
        return customerService.getCustomerByEmail(email)
                .map(customer -> ResponseEntity.ok(customer))
                .orElseThrow(() -> new ProductNotFoundException("Customer with email '" + email + "' not found"));
    }

    @PutMapping("/{email}")
    public ResponseEntity<Customer> updateCustomer(
            @PathVariable String email,
            @Valid @RequestBody Customer customer) {
        return customerService.updateCustomer(email, customer)
                .map(updatedCustomer -> ResponseEntity.ok(updatedCustomer))
                .orElseThrow(() -> new ProductNotFoundException("Customer with email '" + email + "' not found"));
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable String email) {
        boolean deleted = customerService.deleteCustomer(email);
        if (!deleted) {
            throw new ProductNotFoundException("Customer with email '" + email + "' not found");
        }
        return ResponseEntity.noContent().build();
    }
}
