package com.ironhack.lab_java_springboot_rest_api.service;

import com.ironhack.lab_java_springboot_rest_api.model.Customer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    private List<Customer> customers = new ArrayList<>();

    public Customer addCustomer(Customer customer) {
        customers.add(customer);
        return customer;
    }

    public List<Customer> getAllCustomers() {
        return customers;
    }

    public Customer getCustomerByEmail(String email) {
        for (Customer customer : customers) {
            if (customer.getEmail().equalsIgnoreCase(email)) {
                return customer;
            }
        }
        return null;
    }

    public Customer updateCustomer(String email, Customer updatedCustomer) {
        for (Customer customer : customers) {
            if (customer.getEmail().equalsIgnoreCase(email)) {
                customer.setName(updatedCustomer.getName());
                customer.setAge(updatedCustomer.getAge());
                customer.setAddress(updatedCustomer.getAddress());
                return customer;
            }
        }
        return null;
    }

    public boolean deleteCustomer(String email) {
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getEmail().equalsIgnoreCase(email)) {
                customers.remove(i);
                return true;
            }
        }
        return false;
    }
}
