package com.fairtok.online_shopping.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ApiService {
    public void isValidApiKey(String apiKey){
        if (apiKey == null || !apiKey.equals("123456")) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "No API key or incorrect key is provided!");
        }
    }
}
