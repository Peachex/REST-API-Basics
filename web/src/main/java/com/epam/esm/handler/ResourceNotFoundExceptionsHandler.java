package com.epam.esm.handler;

import com.epam.esm.exception.ExceptionResponse;
import com.epam.esm.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceNotFoundExceptionsHandler {
    private final HttpStatus status = HttpStatus.NOT_FOUND;

    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handleRuntimeExceptions(ResourceNotFoundException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(e.getLocalizedMessage(), e.getErrorCode());
        exceptionResponse.setErrorCode(status.value() + e.getErrorCode());
        return new ResponseEntity<>(exceptionResponse, status);
    }
}
