package com.ironhack.labweek5.controller;

//Create new customer (with validation)

import com.ironhack.labweek5.model.Customer;
import com.ironhack.labweek5.model.Product;
import com.ironhack.labweek5.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class CustomerController {
    private final CustomerService customerService;

    CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    //Get all customers
    @GetMapping("/customers")
    public List<Customer> getAllCustomers(){
        return customerService.getAllCustomers();
    }

    @PostMapping("/customers")
    public ResponseEntity<Customer> createNewCustomer(@Valid @RequestBody Customer customer){
        Customer newCustomer = customerService.createNewCustomer(customer);

        //building URI
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{email}")
                .buildAndExpand(newCustomer.getEmail())
                .toUri();

        return ResponseEntity.created(location).body(newCustomer);
    }

    //Get customer by email
    @GetMapping("/customer/{email}")
    public Customer getCustomerByEmail(@PathVariable String email){
        return customerService.getCustomerByEmail(email);
    }

    //Update customer
    @PutMapping("/customer/{email}")
    public Customer updateCustomer(
            @PathVariable String email,
            @Valid @RequestBody Customer customer
    ){
        return customerService.updateCustomer(email, customer);
    }

    //Delete customer
    @DeleteMapping("/customer/{email}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable String email){
        customerService.deleteCustomer(email);
    }
}
