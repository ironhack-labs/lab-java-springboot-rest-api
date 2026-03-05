package com.example.demo.controller;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Customer;
import com.example.demo.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for managing Customer resources.
 * Provides endpoints for CRUD operations on customers.
 * All endpoints require a valid API-Key header for authentication.
 */
@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;
    private static final String AUTH_KEY = "123456";

    /**
     * Constructor for injecting the CustomerService dependency.
     *
     * @param customerService The service for customer operations.
     */
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * Checks if the provided API key is unauthorized (null or incorrect).
     *
     * @param apiKey The API key from the request header.
     * @return true if unauthorized, false otherwise.
     */
    private boolean isUnauthorized(String apiKey) {
        return apiKey == null || !apiKey.equals(AUTH_KEY);
    }

    /**
     * Adds a new customer.
     *
     * @param apiKey   The API key header.
     * @param customer The customer to add (validated).
     * @return Response indicating success or unauthorized.
     */
    @PostMapping
    public ResponseEntity<String> addCustomer(@RequestHeader(value = "API-Key", required = false) String apiKey,
                                              @Valid @RequestBody Customer customer) {
        if (isUnauthorized(apiKey)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or missing API key");
        }
        customerService.addCustomer(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body("Customer added successfully!");
    }

    /**
     * Retrieves all customers.
     *
     * @param apiKey The API key header.
     * @return List of all customers or unauthorized response.
     */
    @GetMapping
    public ResponseEntity<?> getAllCustomers(@RequestHeader(value = "API-Key", required = false) String apiKey) {
        if (isUnauthorized(apiKey)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or missing API key");
        }
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    /**
     * Retrieves a customer by email.
     *
     * @param apiKey The API key header.
     * @param email  The email of the customer.
     * @return The customer or not found/unauthorized response.
     */
    @GetMapping("/{email:.+}")
    public ResponseEntity<?> getCustomerByEmail(@RequestHeader(value = "API-Key", required = false) String apiKey,
                                                @PathVariable String email) {
        if (isUnauthorized(apiKey)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or missing API key");
        }
        Customer customer = customerService.getCustomerByEmail(email);
        if (customer == null) {
            throw new ResourceNotFoundException("Customer with email '" + email + "' not found");
        }
        return ResponseEntity.ok(customer);
    }

    /**
     * Updates a customer by email.
     *
     * @param apiKey          The API key header.
     * @param email           The original email of the customer.
     * @param updatedCustomer The updated customer details (validated).
     * @return Success message or not found/unauthorized response.
     */
    @PutMapping("/{email}")
    public ResponseEntity<String> updateCustomer(@RequestHeader(value = "API-Key", required = false) String apiKey,
                                                 @PathVariable String email,
                                                 @Valid @RequestBody Customer updatedCustomer) {
        if (isUnauthorized(apiKey)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or missing API key");
        }
        boolean updated = customerService.updateCustomer(email, updatedCustomer);
        if (!updated) {
            throw new ResourceNotFoundException("Customer with email '" + email + "' not found");
        }
        return ResponseEntity.ok("Customer successfully updated!");
    }

    /**
     * Deletes a customer by email.
     *
     * @param apiKey The API key header.
     * @param email  The email of the customer to delete.
     * @return Success message or not found/unauthorized response.
     */
    @DeleteMapping("/{email}")
    public ResponseEntity<String> deleteCustomer(@RequestHeader(value = "API-Key", required = false) String apiKey,
                                                 @PathVariable String email) {
        if (isUnauthorized(apiKey)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or missing API key");
        }
        boolean deleted = customerService.deleteCustomer(email);
        if (!deleted) {
            throw new ResourceNotFoundException("Customer with email '" + email + "' not found");
        }
        return ResponseEntity.ok("Customer successfully deleted!");
    }
}