package com.springboot_lab.service;

import com.springboot_lab.model.Customer;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CustomerService {

    private final List<Customer> customers = new ArrayList<>();

    public Customer addCustomer(Customer customer) {
        customers.add(customer);
        return customer;
    }

    public List<Customer> getAllCustomers() {
        return new ArrayList<>(customers);
    }

    public Optional<Customer> getCustomerByEmail(String email) {
        return customers.stream()
                .filter(c -> c.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }

    public Optional<Customer> updateCustomer(String email, Customer updatedCustomer) {
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getEmail().equalsIgnoreCase(email)) {
                updatedCustomer.setEmail(email); // Keep original email
                customers.set(i, updatedCustomer);
                return Optional.of(updatedCustomer);
            }
        }
        return Optional.empty();
    }

    public boolean deleteCustomer(String email) {
        return customers.removeIf(c -> c.getEmail().equalsIgnoreCase(email));
    }
}
