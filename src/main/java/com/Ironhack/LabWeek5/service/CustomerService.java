package com.Ironhack.LabWeek5.service;

import com.Ironhack.LabWeek5.model.Customer;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    private List<Customer> customerStorage ;
    @PostConstruct
    private void initStorage() {
            customerStorage = new ArrayList<>(
                List.of(
                         new Customer( "Ginta", "Ginta.Hero@mar.com",13,"WayToHeaven 333"),
                         new Customer( "Alvis", "Alvis.Hero@mar.com",22,"WayToFar 123"),
                         new Customer( "Loco", "Loco.Hero@mar.com",44,"WayToHell 666")
                )
            );
    }
    //bild the list
    public List<Customer> getCustomer() {
        return customerStorage;
    }
    //get data by email
    public Customer getCustomerByEmail(String email){
        for (Customer customer : customerStorage) {
            if (customer.getEmail().equals(email)) {
                return customer;
            }
        }
        return null;
    }
    //add Customer
    public Customer addCustomer(Customer customer){
        Customer customerToAdd = new Customer(customer.getName(), customer.getEmail(), customer.getAge(), customer.getAddress());
        customerStorage.add(customerToAdd);
        return customerToAdd;
    }
    //del Customer
    public void delCustomerByName(String name){
        for (int i = 0; i < customerStorage.size(); i++) {
            if (customerStorage.get(i).getName().equals(name)) {
                customerStorage.remove(i);
                break;
            }
        }
    }
    //update Customer
    public Customer editCustomer(String name, Customer customer) {
        if(customer.getName() !=null && !customer.getName().equals(name)){
            return null;
        }
        //
        for(Customer exisitingCustomer : customerStorage){
            if(exisitingCustomer.getName().equals(name)){
                exisitingCustomer.setName(customer.getName());
                exisitingCustomer.setEmail(customer.getEmail());
                exisitingCustomer.setAge(customer.getAge());
                exisitingCustomer.setAddress(customer.getAddress());
                return exisitingCustomer;
            }
        }
        return null;
    }
}