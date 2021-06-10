package com.epam.esm.handler;

import com.epam.esm.exception.ExceptionResponse;
import com.epam.esm.exception.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceNotFoundExceptionsHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handleRuntimeExceptions(ResourceNotFoundException e) {
        return new ResponseEntity<>(new ExceptionResponse(e.getLocalizedMessage(), e.getErrorCode()),
                ResourceNotFoundException.getStatus());
    }
}
