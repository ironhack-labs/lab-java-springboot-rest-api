package com.ironhack.lab_java_springboot_rest_api.controller;

import com.ironhack.lab_java_springboot_rest_api.exception.CustomerNotFoundException;
import com.ironhack.lab_java_springboot_rest_api.model.Customer;
import com.ironhack.lab_java_springboot_rest_api.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer customer) {
        Customer created = customerService.addCustomer(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @GetMapping("/{email}")
    public ResponseEntity<Customer> getCustomerByEmail(@PathVariable String email) {
        Customer customer = customerService.getCustomerByEmail(email);
        if (customer == null) {
            throw new CustomerNotFoundException(email);
        }
        return ResponseEntity.ok(customer);
    }

    @PutMapping("/{email}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable String email,
                                                   @Valid @RequestBody Customer updatedCustomer) {
        Customer customer = customerService.updateCustomer(email, updatedCustomer);
        if (customer == null) {
            throw new CustomerNotFoundException(email);
        }
        return ResponseEntity.ok(customer);
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable String email) {
        boolean deleted = customerService.deleteCustomer(email);
        if (!deleted) {
            throw new CustomerNotFoundException(email);
        }
        return ResponseEntity.noContent().build();
    }
}
