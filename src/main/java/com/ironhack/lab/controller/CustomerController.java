package com.ironhack.lab.controller;

import com.ironhack.lab.exception.CustomerNotFoundException;
import com.ironhack.lab.model.Customer;
import com.ironhack.lab.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
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
    @ResponseStatus(HttpStatus.CREATED)
    public Customer createCustomer(@Valid @RequestBody Customer customer) {
        return customerService.addCustomer(customer);
    }

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{email}")
    public Customer getCustomerByEmail(@PathVariable String email) {
        return customerService.getCustomerByEmail(email)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with email: " + email));
    }

    @PutMapping("/{email}")
    public Customer updateCustomer(@PathVariable String email,
                                   @Valid @RequestBody Customer customer) {
        return customerService.updateCustomer(email, customer)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with email: " + email));
    }

    @DeleteMapping("/{email}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable String email) {
        boolean deleted = customerService.deleteCustomer(email);
        if (!deleted) {
            throw new CustomerNotFoundException("Customer not found with email: " + email);
        }
    }
}

