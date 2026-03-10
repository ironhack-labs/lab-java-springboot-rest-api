package controller;

import model.Customer;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private static List<Customer> customerList=new ArrayList<>();

    @PostMapping
    public List<Customer> createCustomer(@Valid @RequestBody Customer customer){
       customerList.add(customer);
       return customerList;
    }

    @GetMapping()
    public List<Customer> displayCustomers(){
        return customerList;
    }

    @GetMapping("/{email}")
    public Customer displayCustomerByEmail(@PathVariable String email){
        for(Customer customer:customerList){
            if(email.equals(customer.getEmail())){
                return customer;
            }
        }
        throw new RuntimeException("Model.Customer not found with email"+email);
    }

    @DeleteMapping("/{email}")
    public void deleteCustomer(@PathVariable String email){
        customerList.removeIf(customer -> customer.getEmail().equalsIgnoreCase(email));
    }
}
