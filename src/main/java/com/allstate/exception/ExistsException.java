package com.allstate.exception;


public class ExistsException extends RuntimeException {

    public ExistsException() {
    }

    public ExistsException(String s) {
        super(s);
    }
}
