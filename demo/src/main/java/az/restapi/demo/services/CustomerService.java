package az.restapi.demo.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import az.restapi.demo.maps.UpdateEmailMapper;
import az.restapi.demo.models.Customer;

@Service
public class CustomerService {

    Map<Integer, Customer> customers = new HashMap<>();

    public void newCustomer(Customer customer){
        customers.put(customer.getCustomerId(), customer);
    }

    public List<Customer> getAll(){
        return new ArrayList<Customer>(customers.values());
    }

    public Customer getByMail(String mail){

        return customers.values().stream()
                                .filter(customer -> customer.getMail().equals(mail))
                                .findFirst()
                                .orElse(null);
    }

    public void updateMail(UpdateEmailMapper map){
        
        customers.get(map.getCustomerId()).setMail(map.getNewMail());

    }

    public void deleteCustomer(int id){

        customers.remove(id);
    }
}
