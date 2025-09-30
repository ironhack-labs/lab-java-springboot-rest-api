package com.ironhack.labjavaspringbootrestapisolution.controller;

import com.ironhack.labjavaspringbootrestapisolution.model.Customer;
import com.ironhack.labjavaspringbootrestapisolution.service.CustomerService;
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

    @GetMapping
    public List<Customer> getAllCustomers(@Valid @RequestHeader (value = "API-KEY") String apiKey) {
        if(apiKey == null || !apiKey.equals("123456")){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid API Key");
        }
        return customerService.getAllCustomers();
    }

    @GetMapping("/{email}")
    public Customer getCustomerByEmail(@Valid @PathVariable String email){
        return customerService.getCustomerByEmail(email);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Customer createCustomer(@Valid @RequestBody Customer customer){
        return customerService.createCustomer(customer);
    }

    @PutMapping("/{name}")
    public Customer updateCustomerByName(@PathVariable String name, @Valid @RequestBody Customer customer){
        return customerService.updateCustomerByName(name, customer);
    }

    @DeleteMapping("/{name}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomerByName(@PathVariable String name){
        customerService.deleteCustomerByName(name);
    }

}
