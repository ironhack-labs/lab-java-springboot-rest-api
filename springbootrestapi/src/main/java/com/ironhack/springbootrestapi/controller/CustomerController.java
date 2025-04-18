package com.ironhack.springbootrestapi.controller;

import com.ironhack.springbootrestapi.model.Customer;
import com.ironhack.springbootrestapi.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{email}")
    public Customer getCustomerByEmail(@PathVariable String email) {
        return customerService.getCustomerByEmail(email);
    }

    @PostMapping
    public Customer addCustomer(@RequestBody Customer customer) {
        return customerService.addCustomer(customer);
    }

    @PutMapping("/{email}")
    public Customer updateCustomer(@PathVariable String email, @RequestBody Customer customer) {
        return customerService.updateCustomer(email, customer);
    }

    @DeleteMapping("/{email}")
    public Customer deleteCustomer(@PathVariable String email) {
        return customerService.deleteCustomer(email);
    }
}
