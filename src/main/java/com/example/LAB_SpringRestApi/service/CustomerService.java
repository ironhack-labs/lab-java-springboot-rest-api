package com.example.LAB_SpringRestApi.service;

import com.example.LAB_SpringRestApi.model.Customer;
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

    public Optional<Customer> getByEmail(String email) {
        return customers.stream()
                .filter(c -> c.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }

    public boolean updateCustomer(String email, Customer updatedCustomer) {
        return getByEmail(email).map(c -> {
            c.setName(updatedCustomer.getName());
            c.setAge(updatedCustomer.getAge());
            c.setAddress(updatedCustomer.getAddress());
            return true;
        }).orElse(false);
    }

    public boolean deleteCustomer(String email) {
        return customers.removeIf(c -> c.getEmail().equalsIgnoreCase(email));
    }
}