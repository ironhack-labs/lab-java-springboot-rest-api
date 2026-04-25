package com.ironhacklab5.productapiapplication.service;

import com.ironhacklab5.productapiapplication.exception.CustomerNotFoundException;
import com.ironhacklab5.productapiapplication.model.Customer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service layer for all customer operations.
 * Mirrors the structure of ProductService — in-memory store, CRUD methods.
 */
@Service
public class CustomerService {

    private final List<Customer> customers = new ArrayList<>();

    /**
     * Adds a new customer to the store.
     *
     * @param customer the validated customer to add
     * @return the saved customer
     */
    public Customer addCustomer(Customer customer) {
        customers.add(customer);
        return customer;
    }

    /**
     * Returns a copy of all customers in the store.
     */
    public List<Customer> getAllCustomers() {
        return new ArrayList<>(customers);
    }

    /**
     * Finds a customer by their email address (case-insensitive).
     *
     * @param email the email to search for
     * @return the matching customer
     * @throws CustomerNotFoundException if no customer with that email exists
     */
    public Customer getCustomerByEmail(String email) {
        return customers.stream()
                .filter(c -> c.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElseThrow(() -> new CustomerNotFoundException(email));
    }

    /**
     * Updates an existing customer identified by their email.
     *
     * @param email           the email of the customer to update
     * @param updatedCustomer the new customer data
     * @return the updated customer
     * @throws CustomerNotFoundException if no customer with that email exists
     */
    public Customer updateCustomer(String email, Customer updatedCustomer) {
        Customer existing = getCustomerByEmail(email); // throws if not found

        existing.setName(updatedCustomer.getName());
        existing.setEmail(updatedCustomer.getEmail());
        existing.setAge(updatedCustomer.getAge());
        existing.setAddress(updatedCustomer.getAddress());

        return existing;
    }

    /**
     * Deletes the customer with the given email.
     *
     * @param email the email of the customer to delete
     * @throws CustomerNotFoundException if no customer with that email exists
     */
    public void deleteCustomer(String email) {
        Customer customer = getCustomerByEmail(email); // throws if not found
        customers.remove(customer);
    }
}