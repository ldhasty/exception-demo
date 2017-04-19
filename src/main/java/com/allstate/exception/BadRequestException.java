package com.allstate.exception;

import org.omg.CORBA.BAD_CONTEXT;

import java.util.ArrayList;
import java.util.List;


public class BadRequestException extends Exception{

    List<BadRequestException> badRequestExceptionList = new ArrayList<>();

    public BadRequestException() {
    }

    public BadRequestException(String s) {
        super(s);
    }

    public BadRequestException(List<BadRequestException> badRequestExceptionList) {
        this.badRequestExceptionList = badRequestExceptionList;
    }

    public List<BadRequestException> getBadRequestExceptionList() {
        return badRequestExceptionList;
    }
}
