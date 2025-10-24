package ab.service;

import ab.model.Customer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class CustomerService {
    private final List<Customer> customers = new ArrayList<>();

    public Customer add(Customer c) {
        customers.add(c);
        return c;
    }

    public List<Customer> getAll() {
        return customers;
    }

    public Customer getByEmail(String email) {
        return customers.stream()
                .filter(c -> c.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElse(null);
    }

    public Customer update(String email, Customer update) {
        Customer existing = getByEmail(email);
        if (existing == null) return null;
        existing.setName(update.getName());
        existing.setEmail(update.getEmail());
        existing.setAge(update.getAge());
        existing.setAddress(update.getAddress());
        return existing;
    }

    public boolean delete(String email) {
        Customer existing = getByEmail(email);
        return existing != null && customers.remove(existing);
    }

}
