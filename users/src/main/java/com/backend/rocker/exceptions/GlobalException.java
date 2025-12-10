package com.backend.rocker.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiError> handleUserNotFound(UserNotFoundException ex, WebRequest request) {
        return buildError("USER_NOT_FOUND", "Entered Username is invalid", request, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateRecordFound.class)
    public ResponseEntity<ApiError> handleDuplicateRecord(DuplicateRecordFound ex, WebRequest request) {
        return buildError("DUPLICATE_RECORD_FOUND", ex.getMessage(), request, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidation(MethodArgumentNotValidException ex, WebRequest request) {

        String message = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .findFirst()
                .orElse("Validation error");

        return buildError("VALIDATION_ERROR", message, request, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGeneral(Exception ex, WebRequest request) {
        return buildError("INTERNAL_ERROR", ex.getMessage(), request, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ApiError> buildError(String code, String message, WebRequest request, HttpStatus status) {
        ApiError apiError = new ApiError.Builder()
                .setErrorCode(code)
                .setErrorMessage(message)
                .setDetails(request.getDescription(false))
                .build();

        return new ResponseEntity<>(apiError, status);
    }
}

