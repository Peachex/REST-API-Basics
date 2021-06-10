package com.epam.esm.handler;

import com.epam.esm.exception.ErrorResponse;
import com.epam.esm.exception.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceNotFoundExceptionsHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<ErrorResponse> handleRuntimeExceptions(ResourceNotFoundException e) {
        return new ResponseEntity<>(new ErrorResponse(e.getLocalizedMessage(), e.getErrorCode()),
                ResourceNotFoundException.getStatus());
    }
}
