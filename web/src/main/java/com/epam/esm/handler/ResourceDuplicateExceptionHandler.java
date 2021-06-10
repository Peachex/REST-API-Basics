package com.epam.esm.handler;

import com.epam.esm.exception.ExceptionResponse;
import com.epam.esm.exception.ResourceDuplicateException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceDuplicateExceptionHandler {
    @ExceptionHandler(ResourceDuplicateException.class)
    public final ResponseEntity<ExceptionResponse> handleRuntimeExceptions(ResourceDuplicateException e) {
        return new ResponseEntity<>(new ExceptionResponse(e.getLocalizedMessage(), e.getErrorCode()),
                ResourceDuplicateException.getStatus());
    }
}
