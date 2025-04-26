package com.example.labweek5.service;

import com.example.labweek5.model.Customer;
import com.example.labweek5.model.Product;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    private final List<Customer> customer = new ArrayList<>();
    
    public void addCustomer(@Valid Customer customer) {
    }

    public List<Customer> getAllCustomers() {
        return null;
    }

    public ResponseEntity<Customer> getCustomerByEmail(String email) {
        return null;
    }

    public void uptadeCustomer(String email, @Valid Customer customer) {
    }

    public void deleteCustumer(String email) {
        customer.remove(customer.indexOf(email));
    }
}
