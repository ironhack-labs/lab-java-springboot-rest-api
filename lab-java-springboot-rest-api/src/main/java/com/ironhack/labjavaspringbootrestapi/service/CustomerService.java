package com.ironhack.labjavaspringbootrestapi.service;

import com.ironhack.labjavaspringbootrestapi.model.Customer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    private final List<Customer> customers = new ArrayList<>();


    // Crear cliente
    public void addCustomer(Customer customer){
        customers.add(customer);
    }


    // Obtener todos los clientesss
    public List<Customer> getAllCustomers(){
        return customers;
    }


    // Buscar cliente por emails
    public Customer getCustomerByEmail(String email){

        for(Customer customer : customers){

            if(customer.getEmail().equalsIgnoreCase(email)){
                return customer;
            }
        }

        return null;
    }


    // Actualizar cliente
    public void updateCustomer(String email, Customer updatedCustomer){

        Customer customer = getCustomerByEmail(email);

        if(customer != null){

            customer.setName(updatedCustomer.getName());
            customer.setEmail(updatedCustomer.getEmail());
            customer.setAge(updatedCustomer.getAge());
            customer.setAddress(updatedCustomer.getAddress());
        }
    }


    // Eliminar cliente
    public void deleteCustomer(String email){

        Customer customer = getCustomerByEmail(email);

        if(customer != null){
            customers.remove(customer);
        }
    }
}
