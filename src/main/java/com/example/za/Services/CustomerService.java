package com.example.za.Services;

import com.example.za.Models.Customer;
import com.example.za.Models.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private List<Customer> customers = new ArrayList<>();

    public List<Customer> addCustomer(Customer customer){
        customers.add(customer);
        return customers;
    }
    public boolean updateCustomertByName(String name, Customer updatedCustomer) {
        for (Customer customer : customers) {
            if (customer.getName().equalsIgnoreCase(name)) {
                customer.setName(updatedCustomer.getName());
                customer.setEmail(updatedCustomer.getEmail());
                customer.setAge(updatedCustomer.getAge());
                customer.setAddress(updatedCustomer.getAddress());
                return true;
            }
        }
        return false;
    }
    public List<Customer> getAllCustomers(){
        return customers;
    }
    public Optional<Customer> getCustomersByEmail(String email){
        return  customers.stream().filter(p->p.getEmail().equalsIgnoreCase(email)).findFirst();
    }
    public boolean deleteCustomerByName(String name){return customers.removeIf(p->p.getName().equalsIgnoreCase(name));}
}