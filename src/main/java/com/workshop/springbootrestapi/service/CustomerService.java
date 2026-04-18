package com.workshop.springbootrestapi.service;

import com.workshop.springbootrestapi.model.Customer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    private final List<Customer> customers = new ArrayList<>();

    public Customer addCustomer(Customer customer) {
        customers.add(customer);
        return customer;
    }

    public List<Customer> getAllCustomers() {
        return customers;
    }

    public Customer getCustomerByEmail(String email) {
        return customers.stream()
                .filter(c -> c.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Customer not found: " + email));
    }

    public Customer updateCustomer(String email, Customer updatedCustomer) {
        Customer customer = getCustomerByEmail(email);

        customer.setName(updatedCustomer.getName());
        customer.setAge(updatedCustomer.getAge());
        customer.setAddress(updatedCustomer.getAddress());

        return customer;
    }

    public void deleteCustomer(String email) {
        Customer customer = getCustomerByEmail(email);
        customers.remove(customer);
    }
}