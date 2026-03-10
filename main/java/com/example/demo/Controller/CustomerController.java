package com.example.demo.Controller;

import com.example.demo.Model.Customer;
import com.example.demo.Service.CustomerService;
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

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    private static final String Api_Key="123456";
    public boolean isValidApiKey(String ApiKey){
        return  Api_Key.equals(ApiKey);
    }
    @PostMapping
    public ResponseEntity<?> addNewCustomers(@RequestHeader("API-Key") String apikey, @Valid @RequestBody Customer customer){
       if(!isValidApiKey(apikey)){
           return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Api key");
       }
       customerService.addCustomer(customer);
       return ResponseEntity.status(HttpStatus.CREATED).body(customer);
    }
    @GetMapping
    public ResponseEntity<?> GetAllCustomers(@RequestHeader("API-Key") String apikey){
        if(!isValidApiKey(apikey)){
            return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Api key");
        }
        List<Customer> customers=customerService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }
    @GetMapping("/email")
    public ResponseEntity<?>GetCustomerByEmail(@RequestHeader("API-Key") String apikey,@PathVariable String email){
        if(!isValidApiKey(apikey)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Api Key ");
        }
       Optional<Customer> customer=customerService.getCustomersByEmail(email);
        if(customer.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Requested body is absent");
        }
        return ResponseEntity.ok(customer);
    }
}
