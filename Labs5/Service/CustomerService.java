package com.example.Labs5.Service;

import com.example.Labs5.Customer.Customer;
import com.example.Labs5.ExceptionHandler.GlobalExceptionHandler;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private final List<Customer> customers = new ArrayList<>(List.of(
            new Customer("Alice", "AliceJ@gmail.com", 30, "123 Main St"),
            new Customer("Bob", "BobD@gmail.com", 25, "456 Oak St")
    ));

    public void addCustomer(Customer customer) {
        customers.add(customer);
        if (customer.getName() == null || customer.getName().isEmpty()) {
            throw new GlobalExceptionHandler.MyCustomException("Customer name cannot be null");
        }
            else if (customer.getEmail() == null  || customer.getEmail().isEmpty()) {
                throw new GlobalExceptionHandler.MyCustomException("Customer email cannot be null");
        }
            else if (customer.getAddress() == null || customer.getAddress().isEmpty()) {
                throw new GlobalExceptionHandler.MyCustomException("Customer address cannot be null");
        }
            else if (customer.getAge() <= 0 || customer.getAge() > 100) {
                throw new GlobalExceptionHandler.MyCustomException("Customer age must be a positive number and don't exceed 100 years");
        }
            customers.add(customer);
    }


    public List<Customer> getAllCustomers() {
        return new ArrayList<>(customers);
    }

    public Optional<Customer> getCustomerByEmail(String email) {
        return customers.stream()
                .filter(customer -> customer.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }

    public void updateCustomer(String email, Customer updatedCustomer) {
        getCustomerByEmail(email).ifPresent(customer -> {
            customer.setName(updatedCustomer.getName());
            customer.setAge(updatedCustomer.getAge());
            customer.setAddress(updatedCustomer.getAddress());
        });
    }

    public void deleteCustomer(String email) {
        customers.removeIf(customer -> customer.getEmail().equalsIgnoreCase(email));
    }
}