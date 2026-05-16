package com.ironhack.labjavaspringbootrestapi.controller;

import com.ironhack.labjavaspringbootrestapi.model.Customer;
import com.ironhack.labjavaspringbootrestapi.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;


    // Constructor
    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }


    // Crear cliente
    @PostMapping
    public String createCustomer(
            @Valid @RequestBody Customer customer){
        customerService.addCustomer(customer);

        return "Customerw created successfully";
    }

    // Obtener todos los clientes
    @GetMapping
    public List<Customer> getAllCustomers(){

        return customerService.getAllCustomers();
    }


    // Buscar por email
    @GetMapping("/{email}")
    public Customer getCustomerByEmail(
            @PathVariable String email){

        return customerService.getCustomerByEmail(email);
    }


    // Actualizar cliente
    @PutMapping("/{email}")
    public String updateCustomer(
            @PathVariable String email,
            @Valid@RequestBody Customer customer){

        customerService.updateCustomer(email, customer);

        return "Customer updated";
    }

    // Eliminar cliente
    @DeleteMapping("/{email}")
    public String deleteCustomer(
            @PathVariable String email){

        customerService.deleteCustomer(email);

        return "Customer deleted";
    }

}




