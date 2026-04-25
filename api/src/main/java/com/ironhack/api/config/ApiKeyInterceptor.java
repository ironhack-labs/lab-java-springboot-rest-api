package com.ironhack.api.config;

import com.ironhack.api.exception.MissingApiKeyException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class ApiKeyInterceptor implements HandlerInterceptor {
    
    @Override
    public boolean preHandle(HttpServletRequest request, 
                           HttpServletResponse response, 
                           Object handler) {
        String apiKey = request.getHeader("API-Key");
        if (apiKey == null || !apiKey.equals("123456")) {
            throw new MissingApiKeyException("API-Key inválido o faltante");
        }
        return true;
    }
}
