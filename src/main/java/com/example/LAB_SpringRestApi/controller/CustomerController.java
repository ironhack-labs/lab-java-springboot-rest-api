package com.example.LAB_SpringRestApi.controller;

import com.example.LAB_SpringRestApi.model.Customer;
import com.example.LAB_SpringRestApi.service.CustomerService; // Import mütləqdir!
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    // Constructor Injection (Autowired ehtiyac yoxdur)
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer customer) {
        customerService.addCustomer(customer);
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{email}")
    public Customer getCustomerByEmail(@PathVariable String email) {
        return customerService.getByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
    }

    @PutMapping("/{email}")
    public String updateCustomer(@PathVariable String email, @Valid @RequestBody Customer customer) {
        if (customerService.updateCustomer(email, customer)) {
            return "Customer updated successfully";
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found");
    }

    @DeleteMapping("/{email}")
    public String deleteCustomer(@PathVariable String email) {
        if (customerService.deleteCustomer(email)) {
            return "Customer deleted successfully";
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found");
    }
}