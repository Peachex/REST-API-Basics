package com.epam.esm.handler;

import com.epam.esm.exception.ErrorResponse;
import com.epam.esm.exception.InvalidFieldException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class InvalidFieldExceptionHandler {
    @ExceptionHandler(InvalidFieldException.class)
    public final ResponseEntity<ErrorResponse> handleRuntimeExceptions(InvalidFieldException e) {
        return new ResponseEntity<>(new ErrorResponse(e.getLocalizedMessage(), e.getErrorCode()),
                InvalidFieldException.getStatus());
    }
}
