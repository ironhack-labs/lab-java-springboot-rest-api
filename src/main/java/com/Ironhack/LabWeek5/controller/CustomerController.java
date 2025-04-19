package com.Ironhack.LabWeek5.controller;

import com.Ironhack.LabWeek5.model.Customer;
import com.Ironhack.LabWeek5.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;


    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    //show-time :)
    @GetMapping
    public List<Customer> geCustomer() {
        return customerService.getCustomer();
    }
    //mostra
    @GetMapping("/email/{email}")
    public Customer getCustomerByEmail(@PathVariable String email) {
        return customerService.getCustomerByEmail(email);
    }
    //add customer
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Customer addCustomer(@RequestBody Customer customer){
        return customerService.addCustomer(customer);
    }
    //del customer
    @DeleteMapping("/{name}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delProductById(@PathVariable String name){
        customerService.delCustomerByName(name);
    }
    //edit customer
    @PutMapping("/{name}")
    public Customer editCustomer(@PathVariable String name, @RequestBody Customer customer) {
        return customerService.editCustomer(name, customer);
    }

}