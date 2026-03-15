package com.ironhack.labjavaspringbootrestapi.service;

import com.ironhack.labjavaspringbootrestapi.exception.ResourceNotFoundException;
import com.ironhack.labjavaspringbootrestapi.model.Customer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {
    private final List<Customer> customers = new ArrayList<>();

    public Customer create(Customer customer) {
        customers.add(customer);
        return customer;
    }

    public List<Customer> findAll() {
        return customers;
    }

    public Customer findByEmail(String email) {
        return customers.stream()
                .filter(c -> c.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with email: " + email));
    }

    public Customer update(String email, Customer updatedCustomer) {
        Customer existing = findByEmail(email);
        existing.setName(updatedCustomer.getName());
        existing.setAge(updatedCustomer.getAge());
        existing.setAddress(updatedCustomer.getAddress());
        return existing;
    }

    public void delete(String email) {
        Customer customer = findByEmail(email);
        customers.remove(customer);
    }
}