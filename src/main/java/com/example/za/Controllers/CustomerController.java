package com.example.za.Controllers;

import com.example.za.Models.Customer;
import com.example.za.Models.Product;
import com.example.za.Services.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService customerService;
    private static final String Api_Key="za";

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    public boolean isValidApiKey(String ApiKey){
        return  Api_Key.equals(ApiKey);
    }
    @PostMapping
    public ResponseEntity<?> addNewCustomer(@RequestHeader("API-Key") String apikey, @Valid @RequestBody Customer customer){
        if(!isValidApiKey(apikey)){
            return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("API key is invalid!");
        }
        customerService.addCustomer(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(customer);
    }
    @GetMapping
    public ResponseEntity<?> getAllCustomers(@RequestHeader("API-Key") String apikey){
        if(!isValidApiKey(apikey)){
            return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("API key is invalid!");
        }
        List<Customer> customers=customerService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }
    @PutMapping("/{name}")
    public ResponseEntity<?> updateProductByName(@RequestHeader("API-Key") String apiKey, @PathVariable String name, @Valid @RequestBody Customer customer) {
        if(!isValidApiKey(apiKey)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("API key is invalid!");
        }
        customerService.updateCustomertByName(name, customer);
        return ResponseEntity.ok(customer);
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<?> deleteProductByName(@RequestHeader("API-Key") String apiKey, @PathVariable String name) {
        if(!isValidApiKey(apiKey)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("API key is invalid!");
        }
        customerService.deleteCustomerByName(name);
        return ResponseEntity.status((HttpStatus.NO_CONTENT)).body("Deleted customer");
    }

    @GetMapping("/email")
    public ResponseEntity<?> getCustomerByEmail(@RequestHeader("API-Key") String apikey,@PathVariable String email){
        if(!isValidApiKey(apikey)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("API key is invalid!");
        }
        Optional<Customer> customer=customerService.getCustomersByEmail(email);
        if(customer.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Requested body is empty!");
        }
        return ResponseEntity.ok(customer);
    }
}