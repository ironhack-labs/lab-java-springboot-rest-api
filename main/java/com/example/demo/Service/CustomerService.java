package com.example.demo.Service;

import com.example.demo.Model.Customer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private List<Customer> customers;

    public List<Customer> addCustomer(Customer customer){
        customers.add(customer);
        return customers;
    }
    public List<Customer> getAllCustomers(){
            return customers;
    }
    public Optional<Customer> getCustomersByEmail(String email){
        return  customers.stream().filter(p->p.getEmail().equalsIgnoreCase(email)).findFirst();
    }
    public boolean DeleteCustomer(String name){return customers.removeIf(p->p.getName().equalsIgnoreCase(name));}
}
