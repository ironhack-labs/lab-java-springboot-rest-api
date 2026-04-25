package com.ironhacklab5.productapiapplication.controller;

import com.ironhacklab5.productapiapplication.exception.InvalidApiKeyException;
import com.ironhacklab5.productapiapplication.model.Customer;
import com.ironhacklab5.productapiapplication.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller exposing all customer-related endpoints.
 *
 * Follows the same pattern as ProductController:
 *   - Constructor injection
 *   - API-Key header validation on every request
 *   - Appropriate HTTP status codes on each response
 *
 * Base URL: /customers
 */
@RestController
@RequestMapping("/customers")
public class CustomerController {

    private static final String VALID_API_KEY = "123456";

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * Validates the API-Key header value.
     * Throws InvalidApiKeyException if the key does not match.
     */
    private void validateApiKey(String apiKey) {
        if (!VALID_API_KEY.equals(apiKey)) {
            throw new InvalidApiKeyException();
        }
    }

    // -----------------------------------------------------------------------
    // POST /customers — create a new customer
    // -----------------------------------------------------------------------

    /**
     * Creates a new customer.
     * @Valid ensures all validation annotations on Customer are enforced.
     * Returns 201 Created on success.
     */
    @PostMapping
    public ResponseEntity<Customer> createCustomer(
            @RequestHeader("API-Key") String apiKey,
            @Valid @RequestBody Customer customer) {

        validateApiKey(apiKey);
        Customer saved = customerService.addCustomer(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // -----------------------------------------------------------------------
    // GET /customers — get all customers
    // -----------------------------------------------------------------------

    /**
     * Returns all customers in the store.
     * Returns 200 OK with the list.
     */
    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers(
            @RequestHeader("API-Key") String apiKey) {

        validateApiKey(apiKey);
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    // -----------------------------------------------------------------------
    // GET /customers/{email} — get customer by email
    // -----------------------------------------------------------------------

    /**
     * Returns a single customer by email.
     * Returns 404 Not Found if the customer does not exist.
     */
    @GetMapping("/{email}")
    public ResponseEntity<Customer> getCustomerByEmail(
            @RequestHeader("API-Key") String apiKey,
            @PathVariable String email) {

        validateApiKey(apiKey);
        return ResponseEntity.ok(customerService.getCustomerByEmail(email));
    }

    // -----------------------------------------------------------------------
    // PUT /customers/{email} — update existing customer
    // -----------------------------------------------------------------------

    /**
     * Updates an existing customer identified by email.
     * Returns 404 Not Found if the customer does not exist.
     */
    @PutMapping("/{email}")
    public ResponseEntity<Customer> updateCustomer(
            @RequestHeader("API-Key") String apiKey,
            @PathVariable String email,
            @Valid @RequestBody Customer updatedCustomer) {

        validateApiKey(apiKey);
        return ResponseEntity.ok(customerService.updateCustomer(email, updatedCustomer));
    }

    // -----------------------------------------------------------------------
    // DELETE /customers/{email} — delete customer by email
    // -----------------------------------------------------------------------

    /**
     * Deletes a customer by email.
     * Returns 204 No Content on success, 404 Not Found if not found.
     */
    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deleteCustomer(
            @RequestHeader("API-Key") String apiKey,
            @PathVariable String email) {

        validateApiKey(apiKey);
        customerService.deleteCustomer(email);
        return ResponseEntity.noContent().build();
    }
}