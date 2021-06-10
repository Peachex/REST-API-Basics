package com.epam.esm.exception;

import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

public class ResourceDuplicateException extends RuntimeException {
    private static final HttpStatus status = BAD_REQUEST;
    private String errorCode;
    private String message;

    public ResourceDuplicateException(String errorCode, String message) {
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