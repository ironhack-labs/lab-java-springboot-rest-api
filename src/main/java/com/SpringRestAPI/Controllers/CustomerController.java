package com.SpringRestAPI.Controllers;

import com.SpringRestAPI.Exceptions.MissingApiKeyException;
import com.SpringRestAPI.Models.Customer;
import com.SpringRestAPI.Services.CustomerService;
import jakarta.validation.Valid;
// @Valid Annotations provide a consistent approach to validation, which improves readability and maintainability.
// It gives you automatic error feedback to the client, indicating exactly which fields are incorrect and why.
// @Valid is used for incoming data validation. Like 'Post' and 'Put' requests. It's not needed for others.
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api")
public class CustomerController {

    CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // create new customer
    @PostMapping("/customer")
    public Customer addNewCustomer(
            @RequestHeader("API-Key") String apiKey,
            @Valid @RequestBody Customer customer ) {
        if (!"123456".equals(apiKey)) {
            throw new MissingApiKeyException("API-Key header is missing or invalid");
        }
        Logger myLogger = Logger.getLogger("CustomerController new customer");
        myLogger.info("Adding new customer ");

        return customerService.addNewCustomer(
                customer.getCustomerName(),
                customer.getCustomerEmail(),
                customer.getCustomerAge(),
                customer.getCustomerAddress()
        );
    }

    // get all customer
    @GetMapping("/customer")
    public List<Customer> getCustomers(
            @RequestHeader("API-Key") String apiKey) {
        if (!"123456".equals(apiKey)) {
            throw new RuntimeException("Invalid API Key");
        }

        Logger myLogger = Logger.getLogger("CustomerService get all customers");
        myLogger.info("Getting all customers");

        return customerService.getCustomers();
    }

    // get customer by email
    @GetMapping("/customer/{customerEmail}")
    public List<Customer> getCustomers(
            @RequestHeader("API-Key") String apiKey,
            @PathVariable String customerEmail) {
        if (!"123456".equals(apiKey)) {
            throw new RuntimeException("Invalid API Key");
        }

        Logger myLogger = Logger.getLogger("CustomerService getting customer by email");
        myLogger.info("Getting customer by email");

        return customerService.getCustomerByEmail(customerEmail);
    }

    // update customer name
    @PutMapping("/customer/{currentName}/{newName}")
    public Customer updateCustomerName(
            @RequestHeader("API-Key") String apiKey,
            @Valid @PathVariable String currentName,
            @PathVariable String newName) {
        if (!"123456".equals(apiKey)) {
            throw new RuntimeException("Invalid API Key");
        }

        Logger myLogger = Logger.getLogger("CustomerController update customer name");
        myLogger.info("Updating customer name");

        return customerService.updateCustomerName(currentName, newName);
    }

    // delete customer
    @DeleteMapping("/customer/{customerName}")
    public Customer deleteCustomer(
            @RequestHeader("API-Key") String apiKey,
            @PathVariable String customerName) {
        if (!"123456".equals(apiKey)) {
            throw new RuntimeException("Invalid API Key");
        }

        Logger myLogger = Logger.getLogger("CustomerController delete customer name");
        myLogger.info("Deleting customer name");

        return customerService.deleteCustomer(customerName)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
    }

}