package com.backend.rocker.exceptions;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String errorMessage){
        super(errorMessage);
    }
}
