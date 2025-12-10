package com.backend.rocker.exceptions;

public class DuplicateRecordFound extends RuntimeException{

    public DuplicateRecordFound(String errorMessage){
        super(errorMessage);
    }
}
