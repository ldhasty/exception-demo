package com.allstate.controller;

import com.allstate.domain.Person;
import com.allstate.exception.ApiException;
import com.allstate.exception.BadRequestException;
import com.allstate.exception.ExistsException;
import com.allstate.exception.NotFoundException;
import com.allstate.service.DummyService;
import org.aspectj.weaver.tools.Trace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.EmbeddedServletContainerInitializedEvent;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.SpanAccessor;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.context.ApplicationListener;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class Controller implements ApplicationListener<EmbeddedServletContainerInitializedEvent> {

    private static Logger log = LoggerFactory.getLogger(Controller.class);
    @Autowired
    DummyService dummyService;

    private int port;

    @Autowired
    private Tracer tracer;


    @GetMapping
    public ResponseEntity<Object> testGetMethod(@RequestHeader HttpHeaders headers)  {
        //used for queries that have returned 0 rows
        log.info("Entering testgetMethod");
        log.info("doing something here maybe a serivce call or something");
        log.info("Maybe looking for an object that we will find");
        log.info("Leaving this method with correlationId goodness");
        log.info("Not really leaving but you get the idea lol hopefully this works well with AOP");
        log.info("Maybe we can have the method named logged as well by default via Spring AOP");
        log.info("Will this replace some of the need for the AOP stuff thats up for discussion");
        String traceId = Long.toHexString(tracer.getCurrentSpan().getTraceId());
        log.info( traceId);
        dummyMethod();
        log.info("calling third party service and tagging with correlationId");
        Object response = dummyService.dummyServiceMethod();
        log.info("third party service returned a response and tagging with correlationId");
        return new ResponseEntity<Object>(response,HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Object> testPostMethod( @RequestHeader HttpHeaders headers)  {
        //this is to be used in instances where we are validating data and the inputs that are required are passed down
        //but not valid ie mbrId greater than 9 less than 15 and 5 digits was passed in.
        log.info("Leaving this method with correlationId goodness");
        throw new BadRequestException("Bad Request");
    }

    @DeleteMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public ResponseEntity<Object> testAnotherPostMethod()  {
        //ApiException to wrap any other exceptions that may occur in the code
        //and catching it here logging the real expection at the location it occured ie "in the service" from there
        //a new ApiException will be thrown back to the controller
        throw new ApiException("Something else bad happened");
    }

    @PostMapping("exists")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Object> testExistsPostMethod()  {
        //intended to be used for resources which already exist.  ie a UniqueConstraintViolation
        throw new ExistsException("this already exists");
    }

    @PostMapping ("/validation")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Person testValidationPostMethod(@Valid @RequestBody Person person, Errors errors)  {
        //this is to be used in instances where we are validating data and the inputs that are required are passed down
        //but not valid ie mbrId greater than 9 less than 15 and 5 digits was passed in.
        //this can also return a list of errors by fields as well
        List<BadRequestException> badRequestExceptions  = new ArrayList<>();

        if (errors.hasErrors()) {
            errors.getFieldErrors().stream().forEach(error ->{
                badRequestExceptions.add(new BadRequestException(error.getField() + " " + error.getDefaultMessage()));
            });
            throw new BadRequestException(badRequestExceptions);
        }
        return person;
    }

    public void dummyMethod ()
    {
        log.info("This is a dumy method to see what happens here as well");

    }

    @Override
    public void onApplicationEvent(EmbeddedServletContainerInitializedEvent event) {
        this.port = event.getEmbeddedServletContainer().getPort();
    }
}
