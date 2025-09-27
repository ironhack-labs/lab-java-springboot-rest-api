package com.ironhack.labjavaspringbootrestapisolutions.controllers;

import com.ironhack.labjavaspringbootrestapisolutions.model.Customer;
import com.ironhack.labjavaspringbootrestapisolutions.model.Product;
import com.ironhack.labjavaspringbootrestapisolutions.service.CustomerService;
import com.ironhack.labjavaspringbootrestapisolutions.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping()
public class CustomerController {

    // Uses constructor injection for the CustomerService
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    //   Create new customer (with validation)
    @PostMapping("/customers")
    @ResponseStatus(code= HttpStatus.CREATED)
    public Customer createNewCustomer(@RequestBody @Valid Customer customer){
        return customerService.createNewCustomer(customer);
    }

    //   Get all customers
    @GetMapping("/customers")
    public List<Customer> getAllCustomers(){
        return customerService.getAllCustomers();
    }

    //   Get customer by email
    @GetMapping("/customers/{email}")
    public Customer getCustomerByEmail(@PathVariable String email){
        return customerService.getCustomerByEmail(email);
    }

    //   Update customer
    @PutMapping("/customers/{name}")
    public Customer updateCustomer(@PathVariable String name, @RequestBody @Valid Customer customer){
        return customerService.updateCustomer(name, customer);
    }

    //   Delete customer
    @DeleteMapping("/customers/{name}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable String name){
        customerService.deleteCustomer(name);
    }



}
