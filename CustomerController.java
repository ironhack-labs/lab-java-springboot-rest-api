package com.example.videostreamingservice;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	private final CustomerService customerService;

	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}

	@PostMapping
	public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer customer) {
		return ResponseEntity.status(HttpStatus.CREATED).body(customerService.addCustomer(customer));
	}

	@GetMapping
	public ResponseEntity<List<Customer>> getAllCustomers() {
		return ResponseEntity.ok(customerService.getAllCustomers());
	}

	@GetMapping("/{email}")
	public ResponseEntity<Customer> getCustomerByEmail(@PathVariable String email) {
		Customer customer = customerService.getCustomerByEmail(email)
				.orElseThrow(() -> new CustomerNotFoundException(email));
		return ResponseEntity.ok(customer);
	}

	@PutMapping("/{email}")
	public ResponseEntity<Customer> updateCustomer(
			@PathVariable String email,
			@Valid @RequestBody Customer customer) {
		Customer updatedCustomer = customerService.updateCustomer(email, customer)
				.orElseThrow(() -> new CustomerNotFoundException(email));
		return ResponseEntity.ok(updatedCustomer);
	}

	@DeleteMapping("/{email}")
	public ResponseEntity<Void> deleteCustomer(@PathVariable String email) {
		if (!customerService.deleteCustomer(email)) {
			throw new CustomerNotFoundException(email);
		}

		return ResponseEntity.noContent().build();
	}
}
