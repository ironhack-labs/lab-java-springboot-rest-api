package com.example.labjavaspringbootrestapi.client;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class ProductApiTest {

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        String baseUrl = "http://localhost:8080/products";

        // Headers with API-Key
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("API-Key", "123456");

        // 1️⃣ Add a new product
        String newProductJson = """
                {
                  "name": "Laptop",
                  "price": 1200,
                  "category": "Electronics",
                  "quantity": 5
                }
                """;

        HttpEntity<String> postRequest = new HttpEntity<>(newProductJson, headers);
        ResponseEntity<String> postResponse = restTemplate.postForEntity(baseUrl, postRequest, String.class);
        System.out.println("POST response: " + postResponse.getStatusCode() + " - " + postResponse.getBody());

        // 2️⃣ Get all products
        HttpEntity<Void> getRequest = new HttpEntity<>(headers);
        ResponseEntity<String> getAllResponse = restTemplate.exchange(baseUrl, HttpMethod.GET, getRequest, String.class);
        System.out.println("GET all response: " + getAllResponse.getStatusCode() + " - " + getAllResponse.getBody());

        // 3️⃣ Get product by name
        String productName = "Laptop";
        String getByNameUrl = baseUrl + "/" + productName;
        ResponseEntity<String> getByNameResponse = restTemplate.exchange(getByNameUrl, HttpMethod.GET, getRequest, String.class);
        System.out.println("GET by name response: " + getByNameResponse.getStatusCode() + " - " + getByNameResponse.getBody());
    }
}