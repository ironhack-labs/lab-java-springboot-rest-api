package com.week5.SpringBoot.REST.API.Service;

import com.week5.SpringBoot.REST.API.Model.Customer;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final List<Customer> customers = new ArrayList<>();

    public CustomerService() {
        customers.add(new Customer("Max Mustermann", "max@mail.com", 25, "Berlin"));
        customers.add(new Customer("Anna Schmidt", "anna@mail.com", 30, "Hamburg"));
    }

    public List<Customer> getCustomerList() {
        return customers;
    }

    //
    public Customer save(Customer customer) {
        customers.add(customer);
        return customer; // Gibt den gespeicherten Kunden zurück
    }

    //
    public Optional<Customer> getCustomerByEmail(String email) {
        for (Customer c : customers) {
            if (c.getEmail().equalsIgnoreCase(email)) {
                return Optional.of(c);
            }
        }
        return Optional.empty();
    }

    //
    public Customer updateCustomer(String email, Customer newData) {
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getEmail().equalsIgnoreCase(email)) {
                customers.set(i, newData);
                return newData;
            }
        }
        return null;
    }

    //
    public boolean deleteCustomer(String email) {
        for (Customer c : customers) {
            if (c.getEmail().equalsIgnoreCase(email)) {
                customers.remove(c);
                return true;
            }
        }
        return false;
    }
}


