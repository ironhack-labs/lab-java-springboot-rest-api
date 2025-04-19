package com.example.springbootrestapi.services;

import com.example.springbootrestapi.models.Customer;
import jakarta.annotation.PostConstruct;
import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {
    private List<Customer> allCustomers;
    @PostConstruct
    public void init() {
        allCustomers = new ArrayList<Customer>(
                List.of( new Customer("Salva",
                        "salva@gmail.com",
                        21,
                        "1 Avenue des Champs-élysée"),
                        new Customer("Seb",
                                "seb@gmail.com",
                                26,
                                "2 Avenue des Champs-élysée")
                        )
                );

    }
    public List<Customer> getAllCustomers() {
        return allCustomers;

    }
    public List<Customer> getCustomersbyEmail(@NotBlank String email) {
        List<Customer> customersByEmail = new ArrayList<>();
        for (Customer customer : allCustomers) {
            if (customer.getEmail().equals(email)) {
                customersByEmail.add(customer);
            }
        }
        return customersByEmail;
    }
    public List<Customer> addCustomer(Customer newCustomer) {
        allCustomers.add(newCustomer);
        return allCustomers;
    }

    public Customer updateCustomer(Customer updatedCustomer) {
        for (Customer customer : allCustomers) {
            if (updatedCustomer.getName().equals(customer.getName())) {
                customer.setAddress(updatedCustomer.getAddress());
                customer.setEmail(updatedCustomer.getEmail());
                customer.setAge(updatedCustomer.getAge());
                return customer;
            }

        }
        return null;
    }

    public List<Customer> deleteCustomer(String customer) {
        for (Customer customerToDelete : allCustomers) {
            if (customerToDelete.getName().equals(customer)) {
                allCustomers.remove(customerToDelete);
            }
        }
        return allCustomers;
    }

}
