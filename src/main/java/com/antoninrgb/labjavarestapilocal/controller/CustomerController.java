package com.antoninrgb.labjavarestapilocal.controller;
import com.antoninrgb.labjavarestapilocal.model.Customer;
import com.antoninrgb.labjavarestapilocal.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/*
Create a CustomerController with endpoints to:
Create new customer (with validation)
Get all customers
Get customer by email
Update customer
Delete customer
 */

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer customer) {
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.add(customer));
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAll());
    }

    @GetMapping("/{email}")
    public ResponseEntity<Customer> getByEmail(@PathVariable String email) {
        return ResponseEntity.ok(customerService.getByEmail(email));
    }

    @PutMapping("/{name}")
    public ResponseEntity<Customer> updateCustomer(@Valid @RequestBody Customer customer, @PathVariable String name) {
        return ResponseEntity.ok(customerService.updateCustomer(name, customer));
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Boolean> deleteCustomer(@PathVariable String name) {
        return ResponseEntity.ok(customerService.deleteCustomer(name));
    }
}
