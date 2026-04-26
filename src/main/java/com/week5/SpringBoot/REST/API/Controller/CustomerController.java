package com.week5.SpringBoot.REST.API.Controller;

import com.week5.SpringBoot.REST.API.Model.Customer;
import com.week5.SpringBoot.REST.API.Service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public Customer create(@Valid @RequestBody Customer customer) {
        Logger logger = Logger.getLogger("CustomerAPI");
        logger.info("Creating customer: " + customer.getEmail());

        return customerService.save(customer);
    }

    @GetMapping
    public List<Customer> getAll() {
        Logger logger = Logger.getLogger("CustomerAPI");
        logger.info("Fetching all customers");

        return customerService.getCustomerList();
    }

    @DeleteMapping("/{email}")
    public String delete(@PathVariable String email) {
        Logger logger = Logger.getLogger("CustomerAPI");
        logger.info("Deleting: " + email);

        customerService.deleteCustomer(email);
        return "Customer with email " + email + " deleted.";
    }

    @GetMapping("/{email}")
    public Customer getByEmail(@RequestHeader("API-Key") String key, @PathVariable String email) {
        if (!"123456".equals(key)) throw new RuntimeException("Invalid Key");
        return customerService.getCustomerByEmail(email).orElse(null);
    }

    @PutMapping("/{email}")
    public Customer update(@RequestHeader("API-Key") String key, @PathVariable String email, @Valid @RequestBody Customer customer) {
        if (!"123456".equals(key)) throw new RuntimeException("Invalid Key");
        return customerService.updateCustomer(email, customer);
    }

}
