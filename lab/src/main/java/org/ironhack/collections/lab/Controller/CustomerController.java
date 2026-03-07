package org.ironhack.collections.lab.Controller;

import jakarta.validation.Valid;
import org.ironhack.collections.lab.Model.Customer;
import org.ironhack.collections.lab.Model.Product;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private List<Customer> customers = new ArrayList<>();

    @PostMapping
    public Customer createCustomer(@Valid @RequestBody Customer customer){
        customers.add(customer);
        return customer;
    }

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customers;
    }
    @GetMapping("/{email}")
    public Customer getCustomerByEmail(@PathVariable String email) {
        return customers.stream().filter(p-> p.getEmail().equalsIgnoreCase(email)).findFirst().orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    @PutMapping("/{email}")
    public Customer updateCustomer(@PathVariable String email, @Valid @RequestBody Customer updated) {
        Customer customer = getCustomerByEmail(email);
        customer.setName(updated.getName());
        customer.setAge(updated.getAge());
        customer.setAddress(updated.getAddress());
        return customer;
    }

    @DeleteMapping("/{email}")
    public void deleteCustomer(@PathVariable String email) {
        Customer customer = getCustomerByEmail(email);
        customers.remove(customer);
    }
}
