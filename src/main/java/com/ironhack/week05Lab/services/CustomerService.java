package com.ironhack.week05Lab.services;

import com.ironhack.week05Lab.exceptions.CustomerNotFoundException;
import com.ironhack.week05Lab.models.Customer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Create new customer (with validation)
 * Get all customers
 * Get customer by email
 * Update customer
 * Delete customer
 */

@Service
public class CustomerService {
    private List<Customer> customers = new ArrayList<>();

    public Customer addCustomer(Customer customer) {
        customers.add(customer);
        return customer;
    }

    public List<Customer> getAllCustomers() {
        return customers;
    }

    public void deleteCustomer(String email) {
        customers.removeIf(customer -> customer.getEmail().equalsIgnoreCase(email));
    }

    public Customer getCustomerByEmail(String email) {
        for (Customer customer : customers) {
            if (customer.getEmail().equalsIgnoreCase(email)) {
                return customer;
            }
        }

        throw new CustomerNotFoundException(email);
    }

    public Customer updateCustomer(String email, Customer customer) {
        List<Customer> customers = getAllCustomers();

        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getEmail().equalsIgnoreCase(email)) {
                customers.set(i, customer);
                return customer;
            }
        }

        throw new CustomerNotFoundException(email);
    }
}
