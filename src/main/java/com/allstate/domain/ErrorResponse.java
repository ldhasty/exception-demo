package com.allstate.domain;

import org.springframework.http.HttpStatus;


public class ErrorResponse {

    private String userMsg;
    private String devMessage;
    private HttpStatus status;
    private String correlationId;


    public ErrorResponse(String userMsg, String devMessage, HttpStatus status,String correlationId) {
        this.userMsg = userMsg;
        this.devMessage = devMessage;
        this.status = status;
        this.correlationId = correlationId;
    }

    public String getUserMsg() {
        return userMsg;
    }


    public String getDevMessage() {
        return devMessage;
    }


    public HttpStatus getStatus() {
        return status;
    }

    public String getCorrelationId() {
        return correlationId;
    }
}
