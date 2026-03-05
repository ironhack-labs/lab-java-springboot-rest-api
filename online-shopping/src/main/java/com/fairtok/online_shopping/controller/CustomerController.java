package com.fairtok.online_shopping.controller;

import com.fairtok.online_shopping.classes.Customer;
import com.fairtok.online_shopping.service.ApiService;
import com.fairtok.online_shopping.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService customerService;
    private final ApiService apiService;
    public CustomerController(CustomerService customerService, ApiService apiService){
        this.customerService = customerService;
        this.apiService = apiService;
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer customer, @RequestHeader(value = "API-Key", required = false) String apiKey){
        apiService.isValidApiKey(apiKey);
        Customer created = customerService.create(customer.getName(), customer.getEmail(), customer.getAge(), customer.getAddress());
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{email}")
    public ResponseEntity<Customer> updateProduct(@PathVariable String email, @Valid @RequestBody Customer customer, @RequestHeader(value = "API-Key", required = false) String apiKey){
        apiService.isValidApiKey(apiKey);
        Customer updated = customerService.update(customer.getName(), email, customer.getAge(), customer.getAddress());
        if (updated == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Void> updateProduct(@PathVariable String email, @RequestHeader(value = "API-Key", required = false) String  apiKey){
        apiService.isValidApiKey(apiKey);
        customerService.delete(email);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public List<Customer> allCustomers(@RequestHeader(value = "API-Key", required = false) String apiKey){
        apiService.isValidApiKey(apiKey);
        return customerService.listCustomers();
    }

    @GetMapping("/{email}")
    public Customer listByEmail(@PathVariable String email, @RequestHeader(value = "API-Key", required = false) String apiKey){
        apiService.isValidApiKey(apiKey);
        return customerService.findByEmail(email);
    }

}

