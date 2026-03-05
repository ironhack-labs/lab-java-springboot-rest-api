package com.ironhack.demo.Service;

import com.ironhack.demo.Model.Customer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomerService {

    private final Map<String, Customer> customers = new HashMap<>();

    public Customer save(Customer customer) {
        customers.put(customer.getEmail(), customer);
        return customer;
    }

    public List<Customer> getAllCustomers() {
        return new ArrayList<>(customers.values());
    }

    public Customer getCustomerByEmail(String email) {
        return customers.get(email);
    }

    public Customer updateCustomer(String email, Customer customer) {
        customers.put(email, customer);
        return customer;
    }

    public void deleteCustomer(String email) {
        customers.remove(email);
    }
}










