package com.allstate.exception;


public class ApiException extends Exception {

    //catch all for anything else we want to deal with.  I suggest we catch and log exceptions lower down then re-throw an
    //ApiException with how ever we want to display what happened to the user

    public ApiException() {
    }

    public ApiException(String s) {
        super(s);
    }
}
