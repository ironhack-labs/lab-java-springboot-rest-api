package com.example.demo.Controller;

import com.example.demo.Model.Customer;
import com.example.demo.Service.CustomerService;
import com.example.demo.Service.ProductService;
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
    private final ProductService productService;

    public CustomerController(CustomerService customerService, ProductService productService) {
        this.customerService = customerService;
        this.productService = productService;
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
    @PutMapping("/{name}")
    public ResponseEntity<?>updateCustomerByName(@RequestHeader("API-Key") String apiKey,@PathVariable String name,@RequestBody Customer customer){
        if(!isValidApiKey(apiKey)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Api Key ");
        }
        customerService.updateCustomer(name,customer);
        return ResponseEntity.ok(customer);
    }
    @DeleteMapping("/{name}")
    public ResponseEntity<?>deleteCustomerByName(@RequestHeader("API-Key") String apiKey,@PathVariable String name){
        if(!isValidApiKey(apiKey)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Api Key ");
        }
        customerService.DeleteCustomer(name);
        return ResponseEntity.noContent().build();
    }
}
