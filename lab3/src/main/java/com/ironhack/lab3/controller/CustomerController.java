package com.ironhack.lab3.controller;

import com.ironhack.lab3.model.Customer;
import com.ironhack.lab3.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping ("/api/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

//    Create new customer (with validation)
    @PostMapping
    public Customer createNewCustomer (@Valid @RequestBody Customer customer) {

        return customerService.create(customer);
    }

//    Get all customers
    @GetMapping
    public List <Customer> getAllCustomers () {
        return customerService.findAll();
    }

//    Get customer by email
    @GetMapping ("/email")
    public List <Customer> getCustomersByEmail(@PathVariable String Email,
                                               @Valid @RequestBody Customer customer){
        return customerService.findByEmail("searched email:");
    }

//    Update customer
    @PatchMapping
    public Customer updateCustomer (@PathVariable Long id,
                                    @Valid @RequestBody Customer customer){
        return customerService.update(id,customer);
    }

//    Delete customer
    @GetMapping
    public ResponseEntity<Objects> remove (@PathVariable Long id,
                                           @Valid @RequestBody Customer customer){
        customerService.remove(id);
        return ResponseEntity.noContent().build();
    }

}
