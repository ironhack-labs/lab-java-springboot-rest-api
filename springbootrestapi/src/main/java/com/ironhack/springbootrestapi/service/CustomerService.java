package com.ironhack.springbootrestapi.service;

import com.ironhack.springbootrestapi.exception.CustomerNotFound;
import com.ironhack.springbootrestapi.model.Customer;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    List<Customer> customers;

    @PostConstruct
    public void init() {
        customers = new ArrayList<>(
                List.of(
                    new Customer("Alex", "alex@example.com", 22, "123 Main Street"),
                    new Customer("John", "john@example.com", 30, "456 Elm Street"),
                    new Customer("Jane", "jane@example.com", 35, "789 Oak Street")
                )
        );
    }

    public List<Customer> getAllCustomers() {
        if(customers == null || customers.isEmpty()) {
            throw new CustomerNotFound("No customers found");
        }
        return customers;
    }

    public Customer getCustomerByEmail(String email) {
        for(Customer customer : customers) {
            if(customer.getEmail().equalsIgnoreCase(email)) {
                return customer;
            }
        }
        throw new CustomerNotFound("Customer not found");
    }

    public Customer addCustomer(Customer customer) {
        customers.add(customer);
        return customer;
    }

    public Customer updateCustomer(String email, Customer customer) {
        for(int i = 0; i < customers.size(); i++) {
            if(customers.get(i).getEmail().equalsIgnoreCase(email)) {
                customers.set(i, customer);
                return customer;
            }
        }

        throw new CustomerNotFound("Customer not found");
    }

    public Customer deleteCustomer(String email) {
        for(int i = 0; i < customers.size(); i++) {
            if(customers.get(i).getEmail().equalsIgnoreCase(email)) {
                Customer customer = customers.get(i);
                customers.remove(i);
                return customer;
            }
        }

        throw new CustomerNotFound("Customer not found");
    }

}
