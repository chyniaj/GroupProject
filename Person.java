package com.mycompany.cpscproject;


/**
 * Write a description of class Person here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Person
{
    // instance variables - replace the example below with your own
    protected String firstName;
    protected String lastName;
    protected String email;
    /**
     * Constructor for objects of class Person
     */
    public Person(String firstName, String lastName, String email)
    {
       firstName = this.firstName;
       lastName = this.lastName;
       email = this.email;
    }
    
    public Person(){
        firstName = "Default";
        lastName = "User";
        email = "test@mail.com";
    }

    public String getFirstName()
    {
        return this.firstName;
    }
    
    public String getLastName(){
        return this.lastName;
    }
    
    public String getEmail(){
        return this.email;
    }
}