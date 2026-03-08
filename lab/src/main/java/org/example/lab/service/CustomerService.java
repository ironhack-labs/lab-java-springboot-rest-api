package org.example.lab.service;

import org.example.lab.model.Customer;
import org.example.lab.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    private List<Customer> customers = new ArrayList<>();

    public CustomerService(){
        customers.add(new Customer("Ali Mammadov", "ali.mammadov@gmail.com", 25, "Baku"));
        customers.add(new Customer("Leyla Aliyeva", "leyla.aliyeva@gmail.com", 30, "Ganja"));
        customers.add(new Customer("Murad Hasanov", "murad.hasanov@gmail.com", 22, "Sumqayit"));
        customers.add(new Customer("Nigar Karimova", "nigar.karimova@gmail.com", 28, "Shaki"));
    }

    public List<Customer> getAllCustomers(){
        return customers;
    }

    public void addCustomer(Customer customer){
        if (customer !=null){
            customers.add(customer);
        }
    }
    public Customer getCustomerByEmail(String email){
        for (Customer p : customers){
            if (p.getEmail().equalsIgnoreCase(email)){
                return p;
            }
        }
        return null;
    }
    public Customer updateCustomer(String email, Customer customer){
        Customer c = getCustomerByEmail(email);
        c.setAddress(customer.getAddress());
        c.setAge(customer.getAge());
        c.setName(customer.getName());
        return c;
    }
    public void delete(String email) {
        for (Customer c : customers) {
            if (c.getEmail().equalsIgnoreCase(email)) {
                customers.remove(c);
            }
        }
    }

}
