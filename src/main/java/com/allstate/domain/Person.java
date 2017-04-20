package com.allstate.domain;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;


public class Person {

    @NotEmpty(message = " name cannot be empty")
    private String fname;

    @NotEmpty(message = " name cannot be empty")
    private String lname;

    @Length(min = 9,max = 9,message = "cant be more or less than nine")
    @NotEmpty(message = " cannot be empty")
    private String ssn;


    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }
}
