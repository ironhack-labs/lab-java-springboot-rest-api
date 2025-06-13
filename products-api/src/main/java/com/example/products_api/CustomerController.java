package com.example.products_api;

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

    @ModelAttribute
    public void checkApiKey(@RequestHeader("API-Key") String apiKey) {
        if (!"123456".equals(apiKey)) {
            throw new SecurityException("Invalid API Key");
        }
    }

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody Customer customer) {
        customerService.addCustomer(customer);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public List<Customer> getAll() {
        return customerService.getAll();
    }

    @GetMapping("/{email}")
    public Customer getByEmail(@PathVariable String email) {
        return customerService.getByEmail(email);
    }

    @PutMapping("/{email}")
    public ResponseEntity<Void> update(@PathVariable String email, @Valid @RequestBody Customer customer) {
        customerService.update(email, customer);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Void> delete(@PathVariable String email) {
        customerService.delete(email);
        return ResponseEntity.noContent().build();
    }
}
