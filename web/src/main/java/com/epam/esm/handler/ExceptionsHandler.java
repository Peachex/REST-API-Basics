package com.epam.esm.handler;

import com.epam.esm.error.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class ExceptionsHandler {
    @ExceptionHandler(RuntimeException.class)
    public final ResponseEntity<ErrorResponse> handleRuntimeExceptions(RuntimeException e) {
        return new ResponseEntity<>(new ErrorResponse(e.getLocalizedMessage(), NOT_FOUND.value() + "11"), NOT_FOUND);
    }
}
