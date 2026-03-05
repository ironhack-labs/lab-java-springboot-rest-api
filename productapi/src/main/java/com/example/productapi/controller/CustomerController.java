package com.example.productapi.controller;

import com.example.productapi.model.Customer;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final List<Customer> customers = new ArrayList<>();

    @PostMapping
    public Customer createCustomer(@Valid @RequestBody Customer customer){
        customers.add(customer);
        return customer;
    }

    @GetMapping
    public List<Customer> getAllCustomers(){
        return customers;
    }

    @GetMapping("/{email}")
    public Customer getCustomer(@PathVariable String email){

        for(Customer c : customers){
            if(c.getEmail().equalsIgnoreCase(email)){
                return c;
            }
        }

        throw new RuntimeException("Customer not found");
    }

    @PutMapping("/{email}")
    public Customer updateCustomer(
            @PathVariable String email,
            @Valid @RequestBody Customer updatedCustomer){

        Customer customer = getCustomer(email);

        customer.setName(updatedCustomer.getName());
        customer.setAge(updatedCustomer.getAge());
        customer.setAddress(updatedCustomer.getAddress());

        return customer;
    }

    @DeleteMapping("/{email}")
    public void deleteCustomer(@PathVariable String email){
        Customer customer = getCustomer(email);
        customers.remove(customer);
    }
}