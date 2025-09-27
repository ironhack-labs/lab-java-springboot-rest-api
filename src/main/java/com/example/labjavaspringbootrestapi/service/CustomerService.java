package com.example.labjavaspringbootrestapi.service;

import com.example.labjavaspringbootrestapi.exception.ResourceNotFoundException;
import com.example.labjavaspringbootrestapi.model.Customer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private final List<Customer> customers = new ArrayList<>();

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public List<Customer> getAllCustomers() {
        return customers;
    }

    public Optional<Customer> getCustomerByEmail(String email) {
        return customers.stream()
                .filter(c -> c.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }

    public void updateCustomer(String email, Customer updatedCustomer) {
        Customer existing = getCustomerByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
        existing.setName(updatedCustomer.getName());
        existing.setAge(updatedCustomer.getAge());
        existing.setAddress(updatedCustomer.getAddress());
        existing.setEmail(updatedCustomer.getEmail());
    }

    public void deleteCustomer(String email) {
        boolean removed = customers.removeIf(c -> c.getEmail().equalsIgnoreCase(email));
        if (!removed) throw new ResourceNotFoundException("Customer not found");
    }
}
