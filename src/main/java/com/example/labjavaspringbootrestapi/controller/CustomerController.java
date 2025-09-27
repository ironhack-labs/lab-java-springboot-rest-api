package com.example.labjavaspringbootrestapi.controller;

import com.example.labjavaspringbootrestapi.exception.InvalidApiKeyException;
import com.example.labjavaspringbootrestapi.model.Customer;
import com.example.labjavaspringbootrestapi.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;
    private static final String API_KEY = "123456";

    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    private void checkApiKey(String apiKey) {
        if (!API_KEY.equals(apiKey)) {
            throw new InvalidApiKeyException("Invalid API Key");
        }
    }

    @PostMapping
    public ResponseEntity<String> addCustomer(
            @RequestHeader("API-Key") String apiKey,
            @Valid @RequestBody Customer customer) {
        checkApiKey(apiKey);
        customerService.addCustomer(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body("Customer added successfully");
    }

    @GetMapping
    public List<Customer> getAllCustomers(@RequestHeader("API-Key") String apiKey) {
        checkApiKey(apiKey);
        return customerService.getAllCustomers();
    }

    @GetMapping("/{email}")
    public Customer getCustomerByEmail(
            @RequestHeader("API-Key") String apiKey,
            @PathVariable String email) {
        checkApiKey(apiKey);
        return customerService.getCustomerByEmail(email)
                .orElseThrow(() -> new com.example.labjavaspringbootrestapi.exception.ResourceNotFoundException("Customer not found"));
    }

    @PutMapping("/{email}")
    public String updateCustomer(
            @RequestHeader("API-Key") String apiKey,
            @PathVariable String email,
            @Valid @RequestBody Customer customer) {
        checkApiKey(apiKey);
        customerService.updateCustomer(email, customer);
        return "Customer updated successfully";
    }

    @DeleteMapping("/{email}")
    public String deleteCustomer(
            @RequestHeader("API-Key") String apiKey,
            @PathVariable String email) {
        checkApiKey(apiKey);
        customerService.deleteCustomer(email);
        return "Customer deleted successfully";
    }
    }

