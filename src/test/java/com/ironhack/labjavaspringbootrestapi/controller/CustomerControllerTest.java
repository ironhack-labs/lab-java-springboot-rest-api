package com.ironhack.labjavaspringbootrestapi.controller;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import com.ironhack.labjavaspringbootrestapi.exception.ResourceNotFoundException;
import com.ironhack.labjavaspringbootrestapi.model.Customer;
import com.ironhack.labjavaspringbootrestapi.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CustomerService customerService;

    private Customer createCustomer(String name, String email, int age, String address) {
        return new Customer(name, email, age, address);
    }

    @Test
    void getAll_returnsOk() throws Exception {
        Customer c1 = createCustomer("Ali", "ali@mail.com", 25, "Baku");
        Customer c2 = createCustomer("Vali", "vali@mail.com", 30, "Sumqayit");

        when(customerService.findAll()).thenReturn(List.of(c1, c2));

        mockMvc.perform(get("/customers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("Ali"));
    }

    @Test
    void getByEmail_existingEmail_returnsCustomer() throws Exception {
        Customer response = createCustomer("Ali", "ali@mail.com", 25, "Baku");

        when(customerService.findByEmail("ali@mail.com")).thenReturn(response);

        mockMvc.perform(get("/customers/ali@mail.com"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("ali@mail.com"));
    }

    @Test
    void create_validRequest_returns201() throws Exception {
        Customer savedResponse = createCustomer("New Customer", "new@mail.com", 20, "Sumqayit");

        when(customerService.create(any(Customer.class))).thenReturn(savedResponse);

        String requestBody = """
                {
                    "name": "New Customer",
                    "email": "new@mail.com",
                    "age": 20,
                    "address": "Sumqayit"
                }
                """;

        mockMvc.perform(post("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("New Customer"));
    }

    @Test
    void update_validRequest_returnsUpdatedCustomer() throws Exception {
        Customer updatedResponse = createCustomer("Ali Updated", "ali@mail.com", 26, "Baku Updated");

        when(customerService.update(anyString(), any(Customer.class))).thenReturn(updatedResponse);

        String requestBody = """
                {
                    "name": "Ali Updated",
                    "email": "ali@mail.com",
                    "age": 26,
                    "address": "Baku Updated"
                }
                """;

        mockMvc.perform(put("/customers/ali@mail.com")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Ali Updated"));
    }

    @Test
    void getByEmail_nonExistentEmail_returns404() throws Exception {
        when(customerService.findByEmail("t123@mail.com"))
                .thenThrow(new ResourceNotFoundException("Customer not found"));

        mockMvc.perform(get("/customers/t123@mail.com"))
                .andExpect(status().isNotFound());
    }
}