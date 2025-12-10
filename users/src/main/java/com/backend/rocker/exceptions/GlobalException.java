package com.backend.rocker.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiError> userNotFoundExceptionHandler(UserNotFoundException exception, WebRequest request){
        ApiError apiError = new ApiError
                                .Builder()
                .setErrorCode("USER_NOT_FOUND")
                .setErrorMessage("Entered Username is invalid")
                .setDetails(request.getDescription(false))
                .build();
        return new ResponseEntity<ApiError>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateRecordFound.class)
    public ResponseEntity<ApiError> duplicateRecordFoundExceptionHandler(UserNotFoundException exception, WebRequest request){
        ApiError apiError = new ApiError
                .Builder()
                .setErrorCode("DUPLICATE_RECORD_FOUND")
                .setErrorMessage(String.valueOf(exception.getMessage()))
                .setDetails(request.getDescription(false))
                .build();
        return new ResponseEntity<ApiError>(apiError, HttpStatus.NOT_FOUND);
    }

}
