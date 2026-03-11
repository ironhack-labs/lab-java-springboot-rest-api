package com.ironhack.demo_lab.exception;

import java.time.LocalDateTime;
import java.util.List;

public class ValidationErrorResponse {
    private Integer status;
    private String error;
    private List<String> messages;
    private LocalDateTime timestamp;

    public ValidationErrorResponse(Integer status, String error, List<String> messages){
        this.status = status;
        this.error = error;
        this.messages = messages;
        this.timestamp = LocalDateTime.now();
    }

    public Integer getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public List<String> getMessages() {
        return messages;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
