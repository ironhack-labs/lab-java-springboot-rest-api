package com.ironhack.bootlabweek5new.controller;

    import com.ironhack.bootlabweek5new.model.Customer;
    import com.ironhack.bootlabweek5new.service.CustomerService;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/customers")

public class CustomerController {

    private List<CustomerController> customers = new ArrayList<>();

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CustomerController> createCustomer(@Valid @RequestBody CustomerController customer) {
        customers.add(customer);
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }

    @GetMapping
    public List<CustomerController> getAllCustomers() {

        return  customerService.getCustomers();
    }

    @GetMapping("/{email}")
    public ResponseEntity<CustomerController> getCustomerByEmail(@PathVariable String email) {
        return customers.stream()
                .filter(customer -> customer.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    private String getEmail() {
    }

    @PutMapping("/{email}")
    public ResponseEntity<CustomerController> updateCustomer(@PathVariable String email, @Valid @RequestBody CustomerController updatedCustomer) {
        return null;
    }

    private Object getAge() {
    }

    private void setName(Object name) {
    }

    private Object getName() {
    }

    @DeleteMapping("/{email}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable String email) {
        customers.removeIf(customer -> customer.getEmail().equalsIgnoreCase(email));
    }

}
