package com.example.demo.service;

import com.example.demo.model.Customer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Service class for managing Customer entities.
 * This class provides methods to perform CRUD operations on customers using an in-memory map with email as the key.
 */
@Service
public class CustomerService {

    // In-memory storage using a map with customer email as the key for uniqueness and quick lookups.
    private final Map<String, Customer> customerMap = new HashMap<>();

    /**
     * Adds a new customer to the storage.
     *
     * @param customer The customer to add.
     */
    public void addCustomer(Customer customer) {
        customerMap.put(customer.getEmail(), customer);
    }

    /**
     * Retrieves all customers from the storage.
     *
     * @return A list of all customers.
     */
    public List<Customer> getAllCustomers() {
        return new ArrayList<>(customerMap.values());
    }

    /**
     * Retrieves a customer by their email.
     *
     * @param email The email of the customer to find.
     * @return The customer if found, otherwise null.
     */
    public Customer getCustomerByEmail(String email) {
        return customerMap.get(email);
    }

    /**
     * Updates an existing customer.
     * If the customer's email has changed, updates the key in the map accordingly.
     *
     * @param email            The original email of the customer to update.
     * @param updatedCustomer  The updated customer details.
     * @return true if the customer was updated, false if not found.
     */
    public boolean updateCustomer(String email, Customer updatedCustomer) {
        if (customerMap.containsKey(email)) {
            customerMap.remove(email);
            customerMap.put(updatedCustomer.getEmail(), updatedCustomer);
            return true;
        }
        return false;
    }

    /**
     * Deletes a customer by their email.
     *
     * @param email The email of the customer to delete.
     * @return true if the customer was deleted, false if not found.
     */
    public boolean deleteCustomer(String email) {
        return customerMap.remove(email) != null;
    }
}