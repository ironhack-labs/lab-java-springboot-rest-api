package com.example.products_api;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CustomerService {

    private final List<Customer> customers = new ArrayList<>();

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public List<Customer> getAll() {
        return customers;
    }

    public Customer getByEmail(String email) {
        return customers.stream()
                .filter(c -> c.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Customer not found"));
    }

    public void update(String email, Customer updated) {
        Customer c = getByEmail(email);
        c.setName(updated.getName());
        c.setAge(updated.getAge());
        c.setAddress(updated.getAddress());
    }

    public void delete(String email) {
        Customer c = getByEmail(email);
        customers.remove(c);
    }
}
