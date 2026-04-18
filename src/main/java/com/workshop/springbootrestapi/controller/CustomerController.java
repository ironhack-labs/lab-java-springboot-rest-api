package com.workshop.springbootrestapi.controller;

import com.workshop.springbootrestapi.model.Customer;
import com.workshop.springbootrestapi.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // CREATE
    @PostMapping
    public Customer create(@Valid @RequestBody Customer customer) {
        return customerService.addCustomer(customer);
    }

    // GET ALL
    @GetMapping
    public List<Customer> getAll() {
        return customerService.getAllCustomers();
    }

    // GET BY EMAIL
    @GetMapping("/{email}")
    public Customer getByEmail(@PathVariable String email) {
        return customerService.getCustomerByEmail(email);
    }

    // UPDATE
    @PutMapping("/{email}")
    public Customer update(@PathVariable String email,
                           @RequestBody Customer customer) {
        return customerService.updateCustomer(email, customer);
    }

    // DELETE
    @DeleteMapping("/{email}")
    public void delete(@PathVariable String email) {
        customerService.deleteCustomer(email);
    }
}