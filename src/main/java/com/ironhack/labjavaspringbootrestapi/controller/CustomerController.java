package com.ironhack.labjavaspringbootrestapi.controller;

import com.ironhack.labjavaspringbootrestapi.model.Customer;
import com.ironhack.labjavaspringbootrestapi.service.CustomerService;
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

        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.create(customer));
    }

    @GetMapping
    public List<Customer> getAllCustomers() {

        return customerService.findAll();
    }

    @GetMapping("/{email}")
    public Customer getCustomerByEmail(@PathVariable String email) {

        return customerService.findByEmail(email);
    }

    @PutMapping("/{email}")
    public Customer updateCustomer(@PathVariable String email, @Valid @RequestBody Customer customer) {

        return customerService.update(email, customer);
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable String email) {

        customerService.delete(email);
        return ResponseEntity.noContent().build();
    }
}