package com.example.demo.services;

import com.example.demo.models.Customer;
import com.example.demo.models.Product;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.constructor.SafeConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomerService {

    List<Customer> customers=new ArrayList<>();
    public List<Customer> getAllCustomers(){
        return customers;
    }

    public Customer getCustomerByEmail(String email){
        for(Customer c: customers){
            if(c.getEmail().equalsIgnoreCase(email)){
                return c;
            }
        }
        return null;
    }

    public void addCustomer(Customer customer){
        Customer yeniCustomer=new Customer(customer.getName(),customer.getEmail(),customer.getAge(),customer.getAdress());
        customers.add(yeniCustomer);
    }

    public void updateCustomer(String name, Customer customer){
        for(Customer c: customers){
            if(c.getName().equalsIgnoreCase(name)){
                c.setName(customer.getName());
                c.setEmail(customer.getEmail());
                c.setAdress(customer.getAdress());
                c.setAge(customer.getAge());
            }
        }
    }

    public void deleteCustomer(String name){
        for(Customer c: customers){
            if(c.getName().equalsIgnoreCase(name)){
                customers.remove(c);
            }
    }}

}

