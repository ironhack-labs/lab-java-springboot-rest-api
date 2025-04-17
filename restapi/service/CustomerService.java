package com.ironhack.restapi.service;


import com.ironhack.restapi.model.Customer;
import com.ironhack.restapi.model.Product;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {
    private List<Customer> customersList;

    @PostConstruct
    private void init(){

        customersList = new ArrayList<>();
        customersList.addAll(
                List.of(
                        new Customer(1, "George", "george@gmail.com", 26, "Carrer de Comercio 29"),
                        new Customer(2, "Emily", "emily@gmail.com", 28, "Carrer de Santa Eulalia 129"),
                        new Customer(3, "Mike", "mike@gmail.com", 23, "Carrer de Carrilet 44"),
                        new Customer(4, "Simon", "simon@gmail.com", 30, "Rambla de Prim 55")
                )
        );

        System.out.println("Customers List Initialized");
    }

    // Get all customers
    public List<Customer> getAllCustomers() {
        return new ArrayList<>(customersList);
    }

    // Get customer by email
    public String getCustomerByEmail(String email){
        for (Customer customer : customersList) {
            if (customer.getEmail().equals(email)) {
                return email;
            }
        }
        return null;
    }

    // Add new customer
    public Customer addCustomer(Customer customer) {
        customersList.add(customer);
        return customer;
    }

    // Update customer
    public Customer updateCustomer(int id, Customer customer) {
        if (customer.getId() != id){
            return null;
        }

        for (Customer exisitingCustomer: customersList){
            if (exisitingCustomer.getId() == id){
                exisitingCustomer.setName(customer.getName());
                exisitingCustomer.setEmail(customer.getEmail());
                exisitingCustomer.setAge(customer.getAge());
                exisitingCustomer.setAddress(customer.getAddress());

                return exisitingCustomer;
            }
        }
        return null;
    }

    // Delete customer
    public void deleteCustomerById(int id){
        for (int i = 0; i < customersList.size(); i++) {
            if (customersList.get(i).getId() == id) {
                customersList.remove(i);
                break;
            }
        }
    }
}