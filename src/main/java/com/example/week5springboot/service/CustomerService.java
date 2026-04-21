package com.example.week5springboot.service;

import com.example.week5springboot.dto.CustomerDTO;
import com.example.week5springboot.model.Customer;
import com.example.week5springboot.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    private final List<Customer> customers = new ArrayList<>();

    public Customer addCustomer(CustomerDTO dto) {
        Customer customer = new Customer(dto.getName(), dto.getEmail(), dto.getAge(), dto.getAddress());
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
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found: " + email));
    }

    public Customer updateCustomer(String email, CustomerDTO dto) {
        Customer existing = getCustomerByEmail(email);
        existing.setName(dto.getName());
        existing.setAge(dto.getAge());
        existing.setAddress(dto.getAddress());
        return existing;
    }

    public boolean deleteCustomer(String email) {
        return customers.removeIf(c -> c.getEmail().equalsIgnoreCase(email));
    }
}