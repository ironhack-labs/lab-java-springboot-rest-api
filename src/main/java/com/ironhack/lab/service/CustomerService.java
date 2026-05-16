package com.ironhack.lab.service;

import com.ironhack.lab.model.Customer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        Optional<Customer> existingCustomer = getCustomerByEmail(email);
        if (existingCustomer.isPresent()) {
            Customer customer = existingCustomer.get();
            customer.setName(updatedCustomer.getName());
            customer.setEmail(updatedCustomer.getEmail());
            customer.setAge(updatedCustomer.getAge());
            customer.setAddress(updatedCustomer.getAddress());
            return Optional.of(customer);
        }
        return Optional.empty();
    }

    public boolean deleteCustomer(String email) {
        return customers.removeIf(c -> c.getEmail().equalsIgnoreCase(email));
    }
}

