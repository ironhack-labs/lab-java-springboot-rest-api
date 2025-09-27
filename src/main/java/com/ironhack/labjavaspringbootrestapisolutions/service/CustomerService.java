package com.ironhack.labjavaspringbootrestapisolutions.service;

import com.ironhack.labjavaspringbootrestapisolutions.exception.ProductException;
import com.ironhack.labjavaspringbootrestapisolutions.model.Customer;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    private final List<Customer> customerList = new ArrayList<>();

    @PostConstruct
    private void init() {
        customerList.add(new Customer("Bob", "bob@gmail.com", 18, "Longstreet 56"));
        customerList.add(new Customer("Rob", "rob@gmail.com", 25, "Shortstreet 62"));
        customerList.add(new Customer("John", "john@gmail.com", 62, "Upstream 19"));
        customerList.add(new Customer("Sofia", "sofia@gmail.com", 32, "Yohostreet 23"));

    }

    //  Methods
    //   Create new customer (with validation)
    public Customer createNewCustomer(Customer customer) {
        customerList.add(customer);
        return customer;
    }

    //   Get all customers
    public List<Customer> getAllCustomers() {
        return customerList;
    }

    //   Get customer by email
    public Customer getCustomerByEmail(String email) {
        for (Customer customer : customerList) {
            if (customer.getName().equals(email)) {
                return customer;
            }
        }
        throw new ProductException(email);
    }

    //   Update customer
    public Customer updateCustomer(String name, Customer customer) {
        for (int i = 0; i < customerList.size(); i++) {
            if (customerList.get(i).getName().equals(name)) {
                customerList.get(i).setName(customer.getName());
                customerList.get(i).setEmail(customer.getEmail());
                customerList.get(i).setAge(customer.getAge());
                customerList.get(i).setAddress(customer.getAddress());
                return customerList.get(i);
            }
        }
        throw new ProductException(name);
    }

    //   Delete customer
    public void deleteCustomer(String name) {
        for (int i = 0; i < customerList.size(); i++) {
            if (customerList.get(i).getName().equals(name)) {
                customerList.remove(i);
                return;
            }
        }
    }
}


