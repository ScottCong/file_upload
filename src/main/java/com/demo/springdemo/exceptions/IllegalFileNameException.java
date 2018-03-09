package com.demo.springdemo.exceptions;

public class IllegalFileNameException extends Exception {

    public IllegalFileNameException(){}

    public IllegalFileNameException(String message){
        super(message);
    }
}
