package com.ironhack.api.controller;

import com.ironhack.api.model.Customer;
import com.ironhack.api.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(
            @Valid @RequestBody Customer customer) {
        return ResponseEntity.status(201)
            .body(customerService.addCustomer(customer));
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(
            customerService.getAllCustomers());
    }

    @GetMapping("/{email}")
    public ResponseEntity<Customer> getCustomerByEmail(
            @PathVariable String email) {
        return ResponseEntity.ok(
            customerService.getCustomerByEmail(email));
    }

    @PutMapping("/{email}")
    public ResponseEntity<Customer> updateCustomer(
            @PathVariable String email, 
            @Valid @RequestBody Customer customer) {
        return ResponseEntity.ok(
            customerService.updateCustomer(email, customer));
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deleteCustomer(
            @PathVariable String email) {
        customerService.deleteCustomer(email);
        return ResponseEntity.noContent().build();
    }
}
