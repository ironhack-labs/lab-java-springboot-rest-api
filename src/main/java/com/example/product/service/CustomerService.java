package com.example.product.service;

import com.example.product.model.Customer;
import com.example.product.repository.CustomerRepository;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    // Constructor Injection (no need for @Autowired)
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    public Customer updateCustomer(String email, Customer customer) {
        if (customerRepository.existsById(email)) {
            customer.setEmail(email);
            return customerRepository.save(customer);
        }
        return null;  // Customer not found
    }

    public boolean deleteCustomer(String email) {
        if (customerRepository.existsById(email)) {
            customerRepository.deleteById(email);
            return true;
        }
        return false;  // Customer not found
    }
}
