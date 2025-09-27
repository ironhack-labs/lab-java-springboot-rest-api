package com.example.lab.controller;

import com.example.lab.exception.ApiKeyException;
import com.example.lab.model.Customer;
import com.example.lab.service.CustomerService;
import javax.validation.Valid;
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

    private void assertApiKey(String apiKey) {
        if (!"123456".equals(apiKey)) {
            throw new ApiKeyException("Invalid API key");
        }
    }

    @PostMapping
    public ResponseEntity<Customer> create(@RequestHeader("API-Key") String apiKey,
                                           @Valid @RequestBody Customer customer) {
        assertApiKey(apiKey);
        return ResponseEntity.ok(customerService.create(customer));
    }

    @GetMapping
    public ResponseEntity<List<Customer>> all(@RequestHeader("API-Key") String apiKey) {
        assertApiKey(apiKey);
        return ResponseEntity.ok(customerService.getAll());
    }

    @GetMapping("/{email}")
    public ResponseEntity<Customer> byEmail(@RequestHeader("API-Key") String apiKey,
                                            @PathVariable String email) {
        assertApiKey(apiKey);
        return ResponseEntity.ok(customerService.getByEmail(email));
    }

    @PutMapping("/{email}")
    public ResponseEntity<Customer> update(@RequestHeader("API-Key") String apiKey,
                                           @PathVariable String email,
                                           @Valid @RequestBody Customer customer) {
        assertApiKey(apiKey);
        return ResponseEntity.ok(customerService.update(email, customer));
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Void> delete(@RequestHeader("API-Key") String apiKey,
                                       @PathVariable String email) {
        assertApiKey(apiKey);
        customerService.delete(email);
        return ResponseEntity.noContent().build();
    }
}
