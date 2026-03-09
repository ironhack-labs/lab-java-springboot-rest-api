package services;
import models.Customer;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    private List<Customer> customers = new ArrayList<>();

    public void createCustomer(Customer customer){
        customers.add(customer);
    }

    public List<Customer> getAllCustomers(){
        return customers;
    }

    public Customer getCustomerByEmail(String email){

        for(Customer c : customers){
            if(c.getEmail().equalsIgnoreCase(email)){
                return c;
            }
        }

        return null;
    }

    public void updateCustomer(String email, Customer newCustomer){

        Customer customer = getCustomerByEmail(email);

        if(customer != null){
            customer.setName(newCustomer.getName());
            customer.setEmail(newCustomer.getEmail());
            customer.setAge(newCustomer.getAge());
            customer.setAddress(newCustomer.getAddress());
        }
    }

    public void deleteCustomer(String email){

        Customer customer = getCustomerByEmail(email);

        if(customer != null){
            customers.remove(customer);
        }
    }
}
