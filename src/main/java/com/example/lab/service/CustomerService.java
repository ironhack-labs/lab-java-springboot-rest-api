package com.example.lab.service;

import com.example.lab.exception.NotFoundException;
import com.example.lab.model.Customer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {
    private final List<Customer> customers = new ArrayList<>();

    public Customer create(Customer c) {
        customers.add(c);
        return c;
    }

    public List<Customer> getAll() {
        return customers;
    }

    public Customer getByEmail(String email) {
        return customers.stream()
                .filter(c -> c.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Customer not found: " + email));
    }

    public Customer update(String email, Customer updated) {
        Customer existing = getByEmail(email);
        existing.setName(updated.getName());
        existing.setAge(updated.getAge());
        existing.setAddress(updated.getAddress());
        existing.setEmail(updated.getEmail());
        return existing;
    }

    public void delete(String email) {
        boolean removed = customers.removeIf(c -> c.getEmail().equalsIgnoreCase(email));
        if (!removed) throw new NotFoundException("Customer not found: " + email);
    }
}
