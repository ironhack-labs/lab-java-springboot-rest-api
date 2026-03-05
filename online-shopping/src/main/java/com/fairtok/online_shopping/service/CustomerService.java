package com.fairtok.online_shopping.service;

import com.fairtok.online_shopping.classes.Customer;
import com.fairtok.online_shopping.classes.Product;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomerService {
    private Map<String, Customer> customers = new HashMap<>();
    public Customer create(String name, String email, int age, String address){
        if(customers.containsKey(name)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Customer " + name + " is exists!");
        }
        Customer created = new Customer(name, email, age, address);
        customers.put(email, created);
        return created;
    }

    public ArrayList<Customer> listCustomers(){
        return new ArrayList<>(customers.values());
    }

    public Customer update(String name, String email, int age, String address){
        Customer existing = findByEmail(email);
        if (existing == null){
            return null;
        }
        Customer replaced = new Customer(name, email, age, address);
        customers.replace(email, replaced);
        return replaced;
    }
    public void delete(String name){
        customers.remove(name);
    }

    public Customer findByEmail(String email){
        return customers.get(email);
    }

}
