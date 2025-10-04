package com.example.LAB.SpringBoot.REST.API.Views;

import com.example.LAB.SpringBoot.REST.API.Models.Customer;
import com.example.LAB.SpringBoot.REST.API.Models.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomerService {

    private final Map<String, Customer> customers = new HashMap<>();

    public Customer addCustomer(Customer customer) {
        customers.put(customer.getEmail(), customer);
        return customer;
    }

    public List<Customer> getAllCustomers() {
        return new ArrayList<>(customers.values());
    }

    public Customer getCustomer(String email) {
        return customers.get(email);
    }

    public Customer updateCustomer(Customer customer) {
        customers.put(customer.getEmail(), customer);
        return customer;
    }

    public void deleteCustomer(String email) {
        customers.remove(email);
    }
}
