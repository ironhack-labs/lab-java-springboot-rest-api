package com.ironhack.springbootrestapi;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CustomerController {

    private List<Customer> customers = new ArrayList<>();

    @PostMapping("/customers")
    public Customer addCustomer(@Valid @RequestBody Customer customer) {
        customers.add(customer);
        return customer;
    }

    @GetMapping("/customers")
    public List<Customer> getAllCustomers() {
        return customers;
    }

    @GetMapping("/customers/{email}")
    public Customer getCustomerByEmail(@PathVariable String email) {
        for (Customer customer : customers) {
            if (customer.getEmail().equalsIgnoreCase(email)) {
                return customer;
            }
        }

        throw new CustomerNotFoundException("Customer not found");
    }

    @PutMapping("/customers/{email}")
    public Customer updateCustomer(@PathVariable String email, @Valid @RequestBody Customer updatedCustomer) {
        Customer customer = getCustomerByEmail(email);

        customer.setName(updatedCustomer.getName());
        customer.setEmail(updatedCustomer.getEmail());
        customer.setAge(updatedCustomer.getAge());
        customer.setAddress(updatedCustomer.getAddress());

        return customer;
    }

    @DeleteMapping("/customers/{email}")
    public String deleteCustomer(@PathVariable String email) {
        Customer customer = getCustomerByEmail(email);
        customers.remove(customer);

        return "Customer deleted";
    }
}