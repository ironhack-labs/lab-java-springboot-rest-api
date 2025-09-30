package com.ironhack.labjavaspringbootrestapisolutions.controller;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.ironhack.labjavaspringbootrestapisolutions.Exceptions;
import com.ironhack.labjavaspringbootrestapisolutions.model.Customer;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    public List<Customer> customers = new ArrayList();

    public Customer customer(String name, String email, int age, String adress) {
        return customer(name, email, age, adress);
    }

    @PostMapping
    public Customer newCustomer(@Valid @RequestBody Customer newCustomer) {
        customers.add(newCustomer);
        return newCustomer;
    }

    @GetMapping("/All")
    public List<Customer> getAllCustomers() {
        return customers;
    }

    @GetMapping("/getByEmail")
    public Customer getByEmail(String email) {
        for (Customer p : customers) {
            if (p.getEmail().equals(email)) {
                return p;
            } else
                throw new RuntimeException("Customer not found");
        }
        return null;
    }

    @PutMapping("/{name}")
    public Customer update(@PathVariable String name, @Valid @RequestBody Customer customer) {
        for (Customer updatedCustomer : customers) {
            if (updatedCustomer.getName().equals(name)) {
                updatedCustomer.setEmail(updatedCustomer.getEmail());
                updatedCustomer.setAge(updatedCustomer.getAge());
                updatedCustomer.setAdress(updatedCustomer.getAdress());
                return updatedCustomer;
            }
        }
        throw new RuntimeException("Customer not found");
    }


    @DeleteMapping(("/{name}"))
    public Customer delete(@PathVariable String name) {
        for (Customer deletedCustomer : customers) {
            if (deletedCustomer.getName().equals(name)) {
                customers.remove(deletedCustomer);
                return deletedCustomer;
            }
        }
        throw new RuntimeException("Customer not found");
    }
}
