package com.antoninrgb.labjavarestapilocal.service;
import com.antoninrgb.labjavarestapilocal.model.Customer;
import com.antoninrgb.labjavarestapilocal.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    private List<Customer> customerList = new ArrayList<>(List.of(
            new Customer("AA", "aaa@aaa.com", 20, "A, via A numero AA"),
            new Customer("BB", "bbb@bbb.com", 21, "B, via B numero BB"),
            new Customer("CC", "ccc@ccc.com", 25, "C, via C numero CC")
    ));

    public Customer add(Customer customer) {
        customerList.add(customer);
        return customer;
    }

    public List<Customer> getAll() {
        return customerList;
    }

    public Customer getByEmail(String email) {
        for (Customer customer : customerList) {
            if (email.equalsIgnoreCase(customer.getEmail())) {
                return customer;
            }
        }
        return null;
    }

    public Customer updateCustomer(String name, Customer customer) {
        for (int i = 0; i < customerList.size(); i++) {
            if (customerList.get(i).getName().equalsIgnoreCase(name)) {
                customerList.set(i, customer);
                return customer;
            }
        }
        return null;
    }

    public boolean deleteCustomer(String name) {
        for (int i = 0; i < customerList.size(); i++) {
            if (customerList.get(i).getName().equalsIgnoreCase(name)) {
                customerList.remove(i);
                return true;
            }
        }
        return false;
    }
}
