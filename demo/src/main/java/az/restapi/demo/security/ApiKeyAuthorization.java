package az.restapi.demo.security;

import org.springframework.stereotype.Component;

@Component
public class ApiKeyAuthorization {

    private static final String REQUIRED_KEY = "123456";

    public boolean isUnauthorized(String apiKey) {
        return apiKey == null || !apiKey.equals(REQUIRED_KEY);
    }
}