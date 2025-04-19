package com.example.springbootrestapi.controllers;

import com.example.springbootrestapi.models.Customer;
import com.example.springbootrestapi.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/customers")
public class CustomerController {
    private final CustomerService customerService;
    public CustomerController(CustomerService customers) {
        this.customerService = customers;

    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public List<Customer> addCustomer(@RequestBody Customer customer) {
        return customerService.addCustomer(customer);
    }

    @GetMapping
    public List<Customer> getCustomers() {
        return customerService.getAllCustomers();
    }
    @GetMapping("byEmail/{email}")
    public List<Customer> getCustomerByEmail(@PathVariable("email") String email) {
        return customerService.getCustomersbyEmail(email);
    }
    @PutMapping
    public Customer updateCustomer(@RequestBody Customer customer) {
        return customerService.updateCustomer(customer);

    }
    @DeleteMapping("/{name}")
    public List<Customer> deleteCustomer(@PathVariable String name) {
        return customerService.deleteCustomer(name);
    }

}
