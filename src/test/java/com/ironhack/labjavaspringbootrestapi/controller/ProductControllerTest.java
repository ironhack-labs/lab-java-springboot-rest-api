package com.ironhack.labjavaspringbootrestapi.controller;

import com.ironhack.labjavaspringbootrestapi.exception.ResourceNotFoundException;
import com.ironhack.labjavaspringbootrestapi.model.Product;
import com.ironhack.labjavaspringbootrestapi.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProductService productService;

    private static final String VALID_API_KEY = "123456";

    private Product createProduct(String name, String category, int quantity, double price) {
        return new Product(name, category, quantity, price);
    }

    @Test
    void getAll_withValidKey_returnsOk() throws Exception {
        Product p1 = createProduct("Laptop", "Electronics", 5, 1500.0);

        when(productService.findAll()).thenReturn(List.of(p1));

        mockMvc.perform(get("/products")
                        .header("API-Key", VALID_API_KEY))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Laptop"))
                .andExpect(jsonPath("$[0].category").value("Electronics"));
    }

    @Test
    void create_validRequest_returns201() throws Exception {
        Product response = createProduct("Phone", "Electronics", 10, 800.0);

        when(productService.create(any(Product.class))).thenReturn(response);

        String requestBody = """
                {
                    "name": "Phone",
                    "price": 800.0,
                    "category": "Electronics",
                    "quantity": 10
                }
                """;

        mockMvc.perform(post("/products")
                        .header("API-Key", VALID_API_KEY)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Phone"));
    }

    @Test
    void update_validRequest_returnsOk() throws Exception {
        Product response = createProduct("Updated Laptop", "Electronics", 3, 1400.0);

        when(productService.update(anyString(), any(Product.class))).thenReturn(response);

        String requestBody = """
                {
                    "name": "Updated Laptop",
                    "price": 1400.0,
                    "category": "Electronics",
                    "quantity": 3
                }
                """;

        mockMvc.perform(put("/products/Laptop")
                        .header("API-Key", VALID_API_KEY)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Laptop"));
    }

    @Test
    void getByPriceRange_returnsList() throws Exception {
        Product p = createProduct("Cheap Item", "Misc", 100, 15.0);

        when(productService.findByPriceRange(10.0, 20.0)).thenReturn(List.of(p));

        mockMvc.perform(get("/products/price")
                        .header("API-Key", VALID_API_KEY)
                        .param("min", "10.0")
                        .param("max", "20.0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    void requestWithInvalidApiKey_ShouldReturn401() throws Exception {
        mockMvc.perform(get("/products")
                        .header("API-Key", "wrong-key"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void getProductByName_NotFound_ShouldReturn404() throws Exception {
        when(productService.findByName("NonExistentProduct"))
                .thenThrow(new ResourceNotFoundException("Product not found"));

        mockMvc.perform(get("/products/NonExistentProduct")
                        .header("API-Key", VALID_API_KEY))
                .andExpect(status().isNotFound());
    }
}