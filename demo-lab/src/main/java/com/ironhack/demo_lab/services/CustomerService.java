package com.ironhack.demo_lab.services;

import com.ironhack.demo_lab.exception.ResourceNotFoundException;
import com.ironhack.demo_lab.models.Customer;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomerService {
    private static Long nextId = 1L;

    private Map<Long, Customer> customers = new HashMap<>();

    public CustomerService(){
        Customer customer1 = new Customer("Nihad", "nihadsmail@gmail.com", 19, "Baku City");
        Customer customer2 = new Customer("Ulvi", "ulvismail@gmail.com", 20, "Khirdalan City");
        customer1.setId(nextId++);
        customer2.setId(nextId++);
        customers.put(customer1.getId(), customer1);
        customers.put(customer2.getId(), customer2);
    }

    public List<Customer> getAll(){
        return customers.values().stream().toList();
    }

    public Customer getById(Long id){
        Customer foundCustomer = customers.get(id);
        if (foundCustomer == null){
            throw new ResourceNotFoundException("Customer", id);
        }
        return foundCustomer;
    }

    public List<Customer> getByEmail(String email){
        return customers.values().stream().filter(c -> c.getEmail().toLowerCase().contains(email.toLowerCase())).toList();
    }

    public Customer create(Customer customer){
        Customer createdCustomer = new Customer();
        createdCustomer.setId(nextId++);
        createdCustomer.setName(customer.getName());
        createdCustomer.setAge(customer.getAge());
        createdCustomer.setEmail(customer.getEmail());
        createdCustomer.setAddress(customer.getAddress());
        customers.put(createdCustomer.getId(), createdCustomer);
        return createdCustomer;
    }


    public Customer update(Long id, Customer customer){
        Customer foundCustomer = getById(id);
        foundCustomer.setName(customer.getName());
        foundCustomer.setAge(customer.getAge());
        foundCustomer.setEmail(customer.getEmail());
        foundCustomer.setAddress(customer.getAddress());
        return foundCustomer;
    }

    public void delete(Long id){
        getById(id);
        customers.remove(id);
    }

}
