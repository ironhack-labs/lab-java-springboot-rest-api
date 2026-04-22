package com.SpringRestAPI.Services;

import com.SpringRestAPI.Models.Customer;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private List<Customer> customers;

    public CustomerService() {
        setCustomerList();
    }

    // creating customers
    public void setCustomerList() {
        customers = new ArrayList<>();

        customers.add(new Customer("Eren", "eren@test.com", 20, "Hamburg, Germany"));
        customers.add(new Customer("Mikasa", "mikasa@test.com", 20, "Berlin, Germany"));
        customers.add(new Customer("Armin", "armin@test.com", 20, "Leipzig, Germany"));
    }

    // get all customers
    public List<Customer> getCustomers() {
        return customers;
    }

    // add new customer
    public Customer addNewCustomer(String customerName, String customerEmail, int customerAge, String customerAddress) {
        Customer newCustomer = new Customer(customerName, customerEmail, customerAge, customerAddress);
        customers.add(newCustomer);
        return newCustomer;
    }

    // get customer by email
    public List<Customer> getCustomerByEmail(String email) {
        List<Customer> customerList = new ArrayList<>();
        for (Customer customer : customers) {
            if (customer.getCustomerEmail().equalsIgnoreCase(email)) {
                customerList.add(customer);
            }
        }
        return customerList;
    }

    // update customer
    public Customer updateCustomerName(String currentName, String newName) {
        for (Customer customer : customers) {
            if (customer.getCustomerName().equalsIgnoreCase(currentName)) {
                customer.setCustomerName(newName);
                return customer;
            }
        }
        throw new RuntimeException("Customer not found");
    }

    // delete customer
    public Optional<Customer> deleteCustomer(String customerName) { /// Optional because we're deleting one
        Optional<Customer> found = customers.stream() ///  This takes your list and turns into Stream.
                /// Stream lets you process elements one by one instead of writing loops manually

                .filter(p -> p.getCustomerName().equals(customerName))
                ///  This keeps only the customers that match the name
                ///  p -> ... lambda function (short version of a method)

                .findFirst();
                ///  Gives the first element from the filtered results.

        found.ifPresent(customers::remove);
        ///  if a product was found, then execute this code
        ///  Long version:
        /*
            if (found.isPresent()) {
        productList.remove(found.get());
            }
         */
        return found;
        ///  Returns results or Exception if not found
    }





}
