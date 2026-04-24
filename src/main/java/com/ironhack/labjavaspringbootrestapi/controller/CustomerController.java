package com.ironhack.labjavaspringbootrestapi.controller;

import com.ironhack.labjavaspringbootrestapi.model.Customer;
import com.ironhack.labjavaspringbootrestapi.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok( customerService.getAllCustomers());
    }
    @GetMapping("/{email}")
    public ResponseEntity<Customer> getCustomerByEmail(
            @PathVariable String email) {

        Customer customer = customerService.getCustomerByEmail(email);
        return ResponseEntity.ok(customer);
    }

    @PutMapping("/{email}")
    public ResponseEntity<Customer> updateCustomer(
            @PathVariable String email,
            @RequestBody @Valid Customer customer) {

        Customer updatedCustomer = customerService.updateCustomer(email, customer);
        return ResponseEntity.ok(updatedCustomer);
    }
    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deleteCustomer(
            @PathVariable String email) {

        customerService.deleteCustomer(email);
        return ResponseEntity.noContent().build();
    }
}
