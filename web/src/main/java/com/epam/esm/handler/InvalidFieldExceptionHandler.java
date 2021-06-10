package com.epam.esm.handler;

import com.epam.esm.exception.ExceptionResponse;
import com.epam.esm.exception.InvalidFieldException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class InvalidFieldExceptionHandler {
    @ExceptionHandler(InvalidFieldException.class)
    public final ResponseEntity<ExceptionResponse> handleRuntimeExceptions(InvalidFieldException e) {
        return new ResponseEntity<>(new ExceptionResponse(e.getLocalizedMessage(), e.getErrorCode()),
                InvalidFieldException.getStatus());
    }
}
