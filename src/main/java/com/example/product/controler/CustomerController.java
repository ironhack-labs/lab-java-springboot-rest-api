package com.example.product.controler;


import com.example.product.model.Customer;
import com.example.product.service.CustomerService;
import jakarta.validation.Valid;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService customerService;

    // Constructor Injection (no need for @Autowired)
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    // Create new customer with validation
    @PostMapping
    public ResponseEntity<String> createCustomer(@Valid @RequestBody Customer customer) {
        customerService.createCustomer(customer);
        return ResponseEntity.ok("Customer created successfully");
    }

    // Get all customers
    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    // Get customer by email
    @GetMapping("/{email}")
    public ResponseEntity<Customer> getCustomerByEmail(@PathVariable String email) {
        Optional<Customer> customer = customerService.getCustomerByEmail(email);
        return customer.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update customer
    @PutMapping("/{email}")
    public ResponseEntity<String> updateCustomer(@PathVariable String email, @Valid @RequestBody Customer customer) {
        Customer updatedCustomer = customerService.updateCustomer(email, customer);
        return updatedCustomer != null ?
                ResponseEntity.ok("Customer updated successfully") :
                ResponseEntity.notFound().build();
    }

    // Delete customer
    @DeleteMapping("/{email}")
    public ResponseEntity<String> deleteCustomer(@PathVariable String email) {
        boolean isDeleted = customerService.deleteCustomer(email);
        return isDeleted ?
                ResponseEntity.ok("Customer deleted successfully") :
                ResponseEntity.notFound().build();
    }
}
