package com.ironhack.week05Lab.controllers;

import com.ironhack.week05Lab.models.Customer;
import com.ironhack.week05Lab.services.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    public Customer addCustomer(@RequestBody @Valid Customer customer) {
        return customerService.addCustomer(customer);
    }

    @GetMapping()
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("{email}")
    public Customer getCustomerByEmail(@PathVariable @Valid String email) {
        return customerService.getCustomerByEmail(email);
    }

    @PutMapping("{email}")
    @ResponseStatus(HttpStatus.OK)
    public Customer updateCustomer(@PathVariable @Valid String email, @RequestBody @Valid Customer customer) {
        return customerService.updateCustomer(email, customer);
    }

    @DeleteMapping("{email}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable @Valid String email) {
        customerService.deleteCustomer(email);
    }
}