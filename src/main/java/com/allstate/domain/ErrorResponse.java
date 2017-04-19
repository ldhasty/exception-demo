package com.allstate.domain;

import org.springframework.http.HttpStatus;


public class ErrorResponse {

    private String userMsg;
    private String devMessage;
    private HttpStatus status;

    public ErrorResponse(String userMsg, String devMessage, HttpStatus status) {
        this.userMsg = userMsg;
        this.devMessage = devMessage;
        this.status = status;
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


}
