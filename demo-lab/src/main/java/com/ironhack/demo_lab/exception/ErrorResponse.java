package com.ironhack.demo_lab.exception;

import java.time.LocalDateTime;

public class ErrorResponse {
    private Integer status;
    private String error;
    private String message;
    private LocalDateTime timestamp;

    public ErrorResponse(Integer status, String error, String message){
        this.status = status;
        this.error = error;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    public Integer getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
