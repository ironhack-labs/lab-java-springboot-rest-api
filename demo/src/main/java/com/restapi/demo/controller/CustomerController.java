package com.restapi.demo.controller;

import com.restapi.demo.model.Customer;
import com.restapi.demo.service.CustomerService;
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

    // POST /customers - Create new customer
    @PostMapping
    public ResponseEntity<Customer> createCustomer(
            @RequestHeader("API-Key") String apiKey,
            @Valid @RequestBody Customer customer) {
        Customer created = customerService.addCustomer(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // GET /customers - Get all customers
    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers(
            @RequestHeader("API-Key") String apiKey) {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    // GET /customers/{email} - Get customer by email
    @GetMapping("/{email}")
    public ResponseEntity<Customer> getCustomerByEmail(
            @RequestHeader("API-Key") String apiKey,
            @PathVariable String email) {
        return ResponseEntity.ok(customerService.getCustomerByEmail(email));
    }

    // PUT /customers/{email} - Update customer
    @PutMapping("/{email}")
    public ResponseEntity<Customer> updateCustomer(
            @RequestHeader("API-Key") String apiKey,
            @PathVariable String email,
            @Valid @RequestBody Customer customer) {
        return ResponseEntity.ok(customerService.updateCustomer(email, customer));
    }

    // DELETE /customers/{email} - Delete customer
    @DeleteMapping("/{email}")
    public ResponseEntity<String> deleteCustomer(
            @RequestHeader("API-Key") String apiKey,
            @PathVariable String email) {
        customerService.deleteCustomer(email);
        return ResponseEntity.ok("Customer with email '" + email + "' deleted successfully");
    }
}