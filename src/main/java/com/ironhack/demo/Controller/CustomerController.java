package com.ironhack.demo.Controller;

import com.ironhack.demo.Model.Customer;
import com.ironhack.demo.Service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

@PostMapping
    public ResponseEntity<Customer> creatCustomer(@Valid @RequestBody Customer customer){
        Customer newOne=customerService.save(customer);
        return ResponseEntity.ok(newOne);
}

@GetMapping
    public List<Customer> getAll(){
        return  customerService.getAllCustomers();
}
@GetMapping("/{email}")
    public Customer gettByEmail(@PathVariable String email){
        return customerService.getCustomerByEmail(email);
}
@PutMapping("/{email}")
    public ResponseEntity<Customer>uptadeCustom(@PathVariable String email,@RequestBody Customer customer){
       Customer uptaded=customerService.updateCustomer(email,customer);
return ResponseEntity.ok(uptaded);
    }
@DeleteMapping("/{email}")
    public ResponseEntity<Void>customerDeleter(@PathVariable String email){
         customerService.deleteCustomer(email);
return ResponseEntity.noContent().build();
    }




}