package com.example.demo.controllers;

import com.example.demo.models.Customer;
import com.example.demo.services.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

CustomerService customerService;
public CustomerController(CustomerService customerService){
    this.customerService=customerService;
}

@GetMapping
    public List<Customer> getAllCustomers(){
    return customerService.getAllCustomers();
}

@GetMapping("/{email}")
    public Customer getCustomerByName(@PathVariable String email){
    return customerService.getCustomerByEmail(email);
}

@PostMapping("/{name}")
    public void updateCustomer(@PathVariable String name, @RequestBody Customer customer){
    customerService.updateCustomer(name,customer);
}

@DeleteMapping("/{name}")
    public void deleteCustomer(@PathVariable String name){
        customerService.deleteCustomer(name);
    }


}
