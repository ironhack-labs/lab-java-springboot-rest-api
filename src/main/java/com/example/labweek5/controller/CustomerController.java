package com.example.labweek5.controller;

import com.example.labweek5.model.Customer;
import com.example.labweek5.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    @PostMapping
    public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer customer) {
        customerService.addCustomer(customer);
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }
    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }
    @GetMapping("/{email}")
    public ResponseEntity<Customer> getCustomerByEmail(@PathVariable String email) {
        return customerService.getCustomerByEmail(email);
    }
    @PutMapping("/{email}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable String email, @Valid @RequestBody Customer customer) {
        customerService.uptadeCustomer(email, customer);
                return ResponseEntity.ok(customer);
    }
    @DeleteMapping("/{email}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable String email) {
        customerService.deleteCustumer(email);
        return ResponseEntity.noContent().build();
    }
}
