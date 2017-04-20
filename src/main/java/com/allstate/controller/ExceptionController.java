package com.allstate.controller;

import com.allstate.domain.ErrorResponse;
import com.allstate.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.CONFLICT;



@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus( value = HttpStatus.NOT_FOUND )
    protected ErrorResponse handleNotFound(NotFoundException ex) {
        return new ErrorResponse(ex.getMessage(),ex.getMessage(),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus( value = HttpStatus.BAD_REQUEST )
    protected List<ErrorResponse> handleBadRequest(BadRequestException ex) {

        List<ErrorResponse> errorResponseList = new ArrayList<>();
        if (ex.getBadRequestExceptionList().isEmpty())
        {
            errorResponseList.add(new ErrorResponse(ex.getMessage(),ex.getMessage(),HttpStatus.BAD_REQUEST));
        }
        else {
            ex.getBadRequestExceptionList().stream().forEach(error -> {
                errorResponseList.add(new ErrorResponse(error.getMessage(), error.getMessage(), HttpStatus.BAD_REQUEST));
            });
        }
        return errorResponseList;
    }

    @ExceptionHandler(ExistsException.class)
    @ResponseStatus( value = CONFLICT )
    protected ErrorResponse handleAlreadyExistsRequest(ExistsException ex) {
        return new ErrorResponse(ex.getMessage(),ex.getMessage(), CONFLICT);
    }

}
