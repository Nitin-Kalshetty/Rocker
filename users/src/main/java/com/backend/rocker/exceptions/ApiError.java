package com.backend.rocker.exceptions;

import java.time.LocalDateTime;

public class ApiError {

    private final String errorCode;
    private final String errorMessage;
    private final LocalDateTime dateTime;
    private final String details;

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDetails() {
        return details;
    }

    public ApiError(Builder builder) {
        this.errorCode = builder.getErrorCode();
        this.errorMessage = builder.getErrorMessage();
        this.dateTime = LocalDateTime.now();
        this.details = builder.getDetails();
    }

    public static class Builder{
        private String errorCode;
        private String errorMessage;
        private String details;

        public String getErrorCode() {
            return errorCode;
        }

        public Builder setErrorCode(String errorCode) {
            this.errorCode = errorCode;
            return this;
        }

        public String getErrorMessage() {
            return errorMessage;
        }

        public Builder setErrorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
            return this;
        }

        public String getDetails() {
            return details;
        }

        public Builder setDetails(String details) {
            this.details = details;
            return this;
        }

        public ApiError build(){
            return new ApiError(this);
        }
    }
}
