package com.Ironhack.lab_java_springboot_rest_api.controller;

import com.Ironhack.lab_java_springboot_rest_api.model.Customer;
import com.Ironhack.lab_java_springboot_rest_api.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
        Customer customer = customerService.getCustomerByEmail(email);
        if (customer == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found");
        }
        return customer;
    }

    @PutMapping("/{email}")
    public Customer updateCustomer(@PathVariable String email, @Valid @RequestBody Customer customer) {
        boolean isUpdated = customerService.updateCustomer(email, customer);
        if (!isUpdated) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found");
        }
        return customer;
    }

    @DeleteMapping("/{email}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable String email) {
        boolean isDeleted = customerService.deleteCustomer(email);
        if (!isDeleted) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found");
        }
    }
}