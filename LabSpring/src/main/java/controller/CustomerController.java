package controller;
import jakarta.validation.Valid;
import models.Customer;
import org.springframework.web.bind.annotation.*;
import services.CustomerService;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public void createCustomer(@Valid @RequestBody Customer customer) {
        customerService.createCustomer(customer);
    }

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{email}")
    public Customer getCustomer(@PathVariable String email) {
        return customerService.getCustomerByEmail(email);
    }

    @PutMapping("/{email}")
    public void updateCustomer(@PathVariable String email, @Valid @RequestBody Customer customer) {

        customerService.updateCustomer(email, customer);
    }

    @DeleteMapping("/{email}")
    public void deleteCustomer(@PathVariable String email) {
        customerService.deleteCustomer(email);
    }
}