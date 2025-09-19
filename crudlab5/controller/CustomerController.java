package com.crud.crudlab5.controller;

import com.crud.crudlab5.model.Customer;
import com.crud.crudlab5.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private CustomerService customerService;
    private static final String API_KEY = "123456";

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    private void checkApiKey(String key){
        if (key == null || !API_KEY.equals(key)) {
            throw new RuntimeException("Invalid API key");
        }
    }
    @PostMapping
    public ResponseEntity<Customer> addCustomer (@RequestHeader("API-Key") String key,@Valid @RequestBody Customer customer){
        checkApiKey(key);
        return new ResponseEntity <> (customerService.addCustomer(customer), HttpStatus.CREATED);
    }
    @GetMapping
    public List<Customer> getAll(@RequestHeader("API-Key") String key){
        checkApiKey(key);
        return customerService.getAllCustomers();
    }

    @GetMapping("/{email}")
    public Customer getByEmail(@RequestHeader("API-Key") String key, @PathVariable("email") String email){
        checkApiKey(key);
        return customerService.getCustomerByEmail(email);
    }
    @PutMapping("/{email}")
    public Customer update(@RequestHeader("API-Key") String key, @PathVariable("email") String email, @Valid @RequestBody Customer customer){
        checkApiKey(key);
        return customerService.updateCustomer(email, customer);
    }
    @DeleteMapping("/{email}")
    public ResponseEntity<Void> delte(@RequestHeader("API-Key") String key, @PathVariable("email") String email){
        checkApiKey(key);
        customerService.deleteCustomer(email);
        return ResponseEntity.noContent().build();
    }

}
