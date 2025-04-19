package com.ironhack.bootlabweek5new.service;
import com.ironhack.bootlabweek5new.controller.CustomerController;
import com.ironhack.bootlabweek5new.model.Customer;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private List<Customer> customersStorage = new ArrayList<>();


    @PostConstruct
    private void initStorage() {
        customersStorage = new ArrayList<>(
                List.of(
                        new Customer("Jean","jean@email.com",18,"5 calle rua"),
                        new Customer("Albert","albert@email.com",41,"5 calle rua"),
                        new Customer("Josiane","josiane@email.com",54,"5 calle rua"),

                        )
        );

    }




    //Get all customers
    public List<Customer> getAllCustomers() {
        return customersStorage;
    }

    //Get customer by name
    public Customer getCustomerByName(String name){
        for (Customer customer : customersStorage) {
            if (customer.getName().equals(name)) {
                return customer;
            }
        }
        return null;
    }

    //Add a new customer
    public Customer addCustomer(Customer customer){
        Customer customerToAdd = new Customer(customer.getName(), customer.getEmail(),customer.getAge(), customer.getAddress());
        customersStorage.add(customerToAdd);
        return customerToAdd;
    }

    //Update customer
    public Customer updateCustomer(String name, Customer customer) {
        if (customer.getName() != null && customer.getName() != name){
            return null;
        }
        for (Customer exisitingCustomer: customersStorage){
            if (exisitingCustomer.getName() == name){
                exisitingCustomer.setEmail(customer.getEmail());
                exisitingCustomer.setAge(customer.getAge());
                exisitingCustomer.setAddress(customer.getAddress());

                return exisitingCustomer;
            }
        }
        return null;
    }

    //Delete customer
    public void deleteCustomerByName(String name){
        for (int i = 0; i < customersStorage.size(); i++) {
            if (customersStorage.get(i).getName().equals(name)) {
                customersStorage.remove(i);
                break;
            }
        }
    }

    //Get customers by Email
    public List<Customer> getCustomersByEmail(String email) {
        // Filter customers based on Email and collect them to a list
        return customersStorage.stream()
                .filter(customer -> customer.getEmail().equalsIgnoreCase(email))
                .collect(Collectors.toList());
    }




    public List<CustomerController> getCustomers() {
    }
}

}
