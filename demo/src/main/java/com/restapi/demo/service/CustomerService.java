package com.restapi.demo.service;

import com.restapi.demo.exception.CustomerNotFoundException;
import com.restapi.demo.model.Customer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    private final List<Customer> customers = new ArrayList<>();

    // Add a new customer
    public Customer addCustomer(Customer customer) {
        customers.add(customer);
        return customer;
    }

    // Get all customers
    public List<Customer> getAllCustomers() {
        return new ArrayList<>(customers);
    }

    // Get customer by email
    public Customer getCustomerByEmail(String email) {
        return customers.stream()
                .filter(c -> c.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElseThrow(() -> new CustomerNotFoundException(
                        "Customer not found with email: " + email));
    }

    // Update customer
    public Customer updateCustomer(String email, Customer updatedCustomer) {
        Customer existing = getCustomerByEmail(email);
        existing.setName(updatedCustomer.getName());
        existing.setEmail(updatedCustomer.getEmail());
        existing.setAge(updatedCustomer.getAge());
        existing.setAddress(updatedCustomer.getAddress());
        return existing;
    }

    // Delete customer
    public void deleteCustomer(String email) {
        Customer customer = getCustomerByEmail(email);
        customers.remove(customer);
    }
}