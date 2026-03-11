package com.ironhack.lab3.service;


import com.ironhack.lab3.model.Customer;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
//I know customerSERVICE class is not written in requirements of lab,
// but I didn't know another alternative way to complete the task.

public class CustomerService {
    private final Map<Long, Customer> customers = new HashMap<>();

//    Create new customer (with validation)
//    Get all customers
//    Get customer by email
//    Update customer
//    Delete customer

    public Customer create(Customer customer) {
        Long id = (long) customers.size() + 1;
        customers.put(id,customer);
        return customer;
    }

    public List<Customer> findAll() {
        return new ArrayList<>(customers.values());
    }

    public List <Customer> findByEmail(String email){
        return customers.values().stream().filter(n -> n.getEmail().toLowerCase().contains(email.toLowerCase())).toList();
    }

    public Customer update (Long id , @Valid Customer customer){
        Customer existing = customers.get(id);
        customer.setName(customer.getName());
        customer.setEmail(customer.getEmail());
        customer.setAge(customer.getAge());
        customer.setAddress(customer.getAddress());


        return existing;
    }

    public void remove (Long id) {
        if ( !customers.containsKey(id)){
            throw new RuntimeException("Custoemr not found");

        }
        customers.remove(id);
    }

}
