package com.allstate.exception;


public class ServerException extends RuntimeException{

    public ServerException() {
    }

    public ServerException(String s) {
        super(s);
    }
}
