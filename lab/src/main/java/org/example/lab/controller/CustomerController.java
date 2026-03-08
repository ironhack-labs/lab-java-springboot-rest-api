package org.example.lab.controller;

import jakarta.validation.Valid;
import org.example.lab.model.Customer;
import org.example.lab.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    @PostMapping
    public void creatCustomer(@Valid @RequestBody Customer customer){
        customerService.addCustomer(customer);
    }

    @GetMapping
    public List<Customer> getAllCustomers(){
        return customerService.getAllCustomers();
    }

    @GetMapping("/{email}")
    public Customer getCustomerByEmail(@PathVariable String email){
        return customerService.getCustomerByEmail(email);
    }

    @PutMapping("/{email}")
    public void updateCustomer(@PathVariable String email,
                               @Valid @RequestBody Customer customer) {
        customerService.updateCustomer(email, customer);
    }

    @DeleteMapping("/{email}")
    public void deleteCustomer(@PathVariable String email) {
        customerService.delete(email);
    }
}
