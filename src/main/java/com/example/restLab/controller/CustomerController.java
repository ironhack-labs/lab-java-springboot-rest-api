package com.example.restLab.controller;

import com.example.restLab.model.Customer;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final List<Customer> customers = new ArrayList<>();

    // Helper for API-Key validation
    private void checkApiKey(String key) {
        if (!"123456".equals(key)) {
            throw new RuntimeException("UNAUTHORIZED");
        }
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestHeader("API-Key") String key,@Valid @RequestBody Customer customer) {
        checkApiKey(key);
        customers.add(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(customer);
    }
    @GetMapping
    public List<Customer> getCustomers(){
        return customers;
    }

    @GetMapping("/{email}")
    public ResponseEntity<Customer> getCustomerByEmail(@PathVariable String email){
        return customers.stream()
                .filter(customer -> customer.getEmail().equals(email))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{email}")
    public ResponseEntity<Customer>  updateCustomer(@RequestHeader("API-Key") String key,@PathVariable String name, @Valid @RequestBody Customer customer){
        checkApiKey(key);
        Customer thisCustomer = customers.stream().filter(c -> c.getName().equals(name)).findFirst().orElse(null);
        if(thisCustomer == null){
            return ResponseEntity.notFound().build();
        }
        thisCustomer.setName(customer.getName());
        thisCustomer.setAge(customer.getAge());
        thisCustomer.setAddress(customer.getAddress());
        return ResponseEntity.ok(thisCustomer);
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deleteCustomer(@RequestHeader("API-Key") String key,
                                               @PathVariable String email) {
        checkApiKey(key);
        boolean removed = customers.removeIf(c -> c.getEmail().equalsIgnoreCase(email));
        return removed ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(f -> errors.put(f.getField(), f.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleAuthError(RuntimeException ex) {
        if ("UNAUTHORIZED".equals(ex.getMessage())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid API Key");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }
}
