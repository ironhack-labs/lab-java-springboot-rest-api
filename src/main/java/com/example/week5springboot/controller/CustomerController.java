package com.example.week5springboot.controller;

import com.example.week5springboot.dto.CustomerDTO;
import com.example.week5springboot.model.Customer;
import com.example.week5springboot.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private static final String VALID_API_KEY = "123456";

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    private boolean isInvalidKey(String apiKey) {
        return !VALID_API_KEY.equals(apiKey);
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(
            @RequestHeader(value = "API-Key", required = false) String apiKey,
            @Valid @RequestBody CustomerDTO customerDTO) {

        if (isInvalidKey(apiKey)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Customer saved = customerService.addCustomer(customerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers(@RequestHeader("API-Key") String apiKey) {
        if (isInvalidKey(apiKey)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @GetMapping("/{email}")
    public ResponseEntity<Customer> getCustomerByEmail(
            @RequestHeader("API-Key") String apiKey,
            @PathVariable String email) {

        if (isInvalidKey(apiKey)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Customer customer = customerService.getCustomerByEmail(email);
        return ResponseEntity.ok(customer);
    }

    @PutMapping("/{email}")
    public ResponseEntity<Customer> updateCustomer(
            @RequestHeader("API-Key") String apiKey,
            @PathVariable String email,
            @Valid @RequestBody CustomerDTO customerDTO) {

        if (isInvalidKey(apiKey)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Customer updated = customerService.updateCustomer(email, customerDTO);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deleteCustomer(
            @RequestHeader("API-Key") String apiKey,
            @PathVariable String email) {

        if (isInvalidKey(apiKey)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        boolean deleted = customerService.deleteCustomer(email);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}