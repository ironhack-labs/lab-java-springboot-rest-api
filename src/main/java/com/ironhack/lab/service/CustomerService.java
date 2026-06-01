package com.ironhack.lab.service;

import com.ironhack.lab.exception.CustomerNotFoundException;
import com.ironhack.lab.model.Customer;
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
                .filter(customer -> customer.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElseThrow(() -> new CustomerNotFoundException(email));
    }

    public Customer updateCustomer(String email, Customer updatedCustomer) {
        Customer existingCustomer = getCustomerByEmail(email);
        existingCustomer.setName(updatedCustomer.getName());
        existingCustomer.setEmail(updatedCustomer.getEmail());
        existingCustomer.setAge(updatedCustomer.getAge());
        existingCustomer.setAddress(updatedCustomer.getAddress());
        return existingCustomer;
    }

    public void deleteCustomer(String email) {
        Customer customer = getCustomerByEmail(email);
        customers.remove(customer);
    }
}
