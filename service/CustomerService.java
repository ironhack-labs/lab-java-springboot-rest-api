package com.ironhack.labweek5.service;

import com.ironhack.labweek5.model.Customer;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {
    private final ArrayList<Customer> customers = new ArrayList<>();

    public CustomerService(){
        customers.add(new Customer("Lauren", "lauren@example.com", 23, "Street 123, 00001, City"));
        customers.add(new Customer("Billy", "billy@example.com", 29, "Street 456, 00001, City"));
        customers.add(new Customer("Keith", "keith@example.com", 47, "Street 789, 00002, Other City"));
    }

    //Get all customers
    public List<Customer> getAllCustomers(){
        return customers;
    }

    //Create new customer (with validation)
    public Customer createNewCustomer(Customer customer){
        customers.add(customer);
        return customer;
    }

    //Get customer by email
    public Customer getCustomerByEmail(String email){
        return customers.stream()
                .filter(c -> c.getEmail().equals(email))
                .findFirst()
                .orElseThrow(()-> new RuntimeException("No customer with that email address"));
    }

    //Update customer
    public Customer updateCustomer(String email, Customer customer){
        Customer customerToUpdate = getCustomerByEmail(email);

        customerToUpdate.setName(customer.getName());
        customerToUpdate.setEmail(customer.getEmail());
        customerToUpdate.setAge(customer.getAge());
        customerToUpdate.setAddress(customer.getAddress());

        return customerToUpdate;
    }

    //Delete customer
    public void deleteCustomer(String email){
        Customer customerToDelete = getCustomerByEmail(email);
        customers.remove(customerToDelete);
    }
}