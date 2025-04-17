package com.ironhack.restapi.controller;

import com.ironhack.restapi.model.Customer;
import com.ironhack.restapi.model.Product;
import com.ironhack.restapi.service.CustomerService;
import com.ironhack.restapi.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // Get all customers (GET/customers)
    @GetMapping
    public List<Customer> getCustomer() {
        return customerService.getAllCustomers();
    }

    // Get customer by email (GET/customers/{email})
    @GetMapping("/{email}")
    public String getCustomerByEmail(@PathVariable String email) {
        return customerService.getCustomerByEmail(email);
    }

    // Create new customer (POST/customers)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Customer addCustomer(@RequestBody Customer customer){
        return customerService.addCustomer(customer);
    }

    // Update CUSTOMER
    @PutMapping("/{id}")
    public Customer updateCustomer (@PathVariable int id, @RequestBody Customer customer) {
        return customerService.updateCustomer(id, customer);
    }

    // Delete customer
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomerById(@PathVariable int id){
        customerService.deleteCustomerById(id);
    }
}
