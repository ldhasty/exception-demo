package com.allstate.controller;

import com.allstate.domain.ErrorResponse;
import com.allstate.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;


@RestControllerAdvice
public class ExceptionController {

    @Autowired
    private Tracer tracer;


    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus( value = HttpStatus.NOT_FOUND )
    protected ErrorResponse handleNotFound(NotFoundException ex) {
        return new ErrorResponse(ex.getMessage(),ex.getMessage(),HttpStatus.NOT_FOUND,Long.toHexString(tracer.getCurrentSpan().getTraceId()));
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus( value = HttpStatus.BAD_REQUEST )
    protected List<ErrorResponse> handleBadRequest(BadRequestException ex) {

        List<ErrorResponse> errorResponseList = new ArrayList<>();
        if (ex.getBadRequestExceptionList().isEmpty())
        {
            errorResponseList.add(new ErrorResponse(ex.getMessage(),ex.getMessage(),HttpStatus.BAD_REQUEST,Long.toHexString(tracer.getCurrentSpan().getTraceId())));
        }
        else {
            ex.getBadRequestExceptionList().stream().forEach(error -> {
                errorResponseList.add(new ErrorResponse(error.getMessage(), error.getMessage(), HttpStatus.BAD_REQUEST,Long.toHexString(tracer.getCurrentSpan().getTraceId())));
            });
        }
        return errorResponseList;
    }

    @ExceptionHandler(ExistsException.class)
    @ResponseStatus( value = CONFLICT )
    protected ErrorResponse handleAlreadyExistsRequest(ExistsException ex) {
        return new ErrorResponse(ex.getMessage(),ex.getMessage(), CONFLICT,Long.toHexString(tracer.getCurrentSpan().getTraceId()));
    }

    @ExceptionHandler(ApiException.class)
    @ResponseStatus( value = INTERNAL_SERVER_ERROR )
    protected ErrorResponse handleInternalSeverError(ApiException ex) {
        return new ErrorResponse(ex.getMessage(),ex.getMessage(), INTERNAL_SERVER_ERROR,Long.toHexString(tracer.getCurrentSpan().getTraceId()));
    }
}
