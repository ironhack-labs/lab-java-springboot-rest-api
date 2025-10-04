package com.example.LAB.SpringBoot.REST.API.Controllers;

import com.example.LAB.SpringBoot.REST.API.Models.Customer;
import com.example.LAB.SpringBoot.REST.API.Views.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<Customer> addCustomer(@Valid @RequestBody Customer customer) {
        return ResponseEntity.ok(customerService.addCustomer(customer));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @GetMapping
    public ResponseEntity<Customer> getCustomersByEmail(@RequestParam String email) {
        return ResponseEntity.ok(customerService.getCustomer(email));
    }

    @PutMapping
    public ResponseEntity<Customer> updateCustomer(@Valid @RequestBody Customer customer) {
        return ResponseEntity.ok(customerService.updateCustomer(customer));
    }

    @DeleteMapping
    public ResponseEntity<Customer> deleteCustomer(@RequestParam String email) {
        customerService.deleteCustomer(email);
        return ResponseEntity.noContent().build();
    }

}
