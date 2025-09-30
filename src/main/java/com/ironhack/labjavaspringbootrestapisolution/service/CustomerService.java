package com.ironhack.labjavaspringbootrestapisolution.service;

import com.ironhack.labjavaspringbootrestapisolution.model.Customer;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    private List<Customer> customerList;

    @PostConstruct
    private void init(){
        customerList = new ArrayList<>(List.of(
                new Customer("beejay", "beejay@home.com", 33, "Bee Apartment"),
                new Customer("sam", "sam@home.com", 40, "Sam Apartment"),
                new Customer("maria", "maria@home.com", 25, "Maria Apartment"),
                new Customer("monica", "monica@home.com", 60, "Monica Apartment")
        )
        );
    }

    public List<Customer> getAllCustomers(){
        return customerList;
    }

    public Customer createCustomer(Customer customer){
        customerList.add(customer);
        return customer;
    }

    public Customer getCustomerByEmail(String email){
        for (Customer customer : customerList){
            if (customer.getEmail().equals(email)){
                return customer;
            }
        } return null;
    }

    public Customer updateCustomerByName(String name, Customer customer) {
        for (Customer value : customerList) {
            if (value.getName().equals(name)) {
                value.setName(customer.getName());
                return value;
            }
        }
        return null;
    }

    public void deleteCustomerByName(String name) {
        for (int i = 0; i< customerList.size(); i++){
            if(customerList.get(i).getName().equals(name)){
                customerList.remove(i);
                return;
            }
        }
    }

}
