package com.ironhack.api.service;

import com.ironhack.api.exception.ResourceNotFoundException;
import com.ironhack.api.model.Customer;
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
        return new ArrayList<>(customers);
    }

    public Customer getCustomerByEmail(String email) {
        return customers.stream()
            .filter(c -> c.getEmail().equalsIgnoreCase(email))
            .findFirst()
            .orElseThrow(() -> new ResourceNotFoundException(
                "Cliente no encontrado: " + email));
    }

    public Customer updateCustomer(String email, Customer updated) {
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getEmail().equalsIgnoreCase(email)) {
                updated.setEmail(email);
                customers.set(i, updated);
                return updated;
            }
        }
        throw new ResourceNotFoundException(
            "Cliente no encontrado: " + email);
    }

    public boolean deleteCustomer(String email) {
        boolean removed = customers.removeIf(
            c -> c.getEmail().equalsIgnoreCase(email));
        if (!removed) {
            throw new ResourceNotFoundException(
                "Cliente no encontrado: " + email);
        }
        return true;
    }
}
