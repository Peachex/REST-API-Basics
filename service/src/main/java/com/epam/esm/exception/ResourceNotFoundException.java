package com.epam.esm.exception;

import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

public class ResourceNotFoundException extends RuntimeException {
    private static final HttpStatus status = NOT_FOUND;
    private String errorCode;
    private String message;

    public ResourceNotFoundException(String errorCode, String message) {
        this.errorCode = status.value() + errorCode;
        this.message = message;
    }

    public static HttpStatus getStatus() {
        return status;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
