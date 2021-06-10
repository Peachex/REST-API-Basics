package com.epam.esm.handler;

import com.epam.esm.exception.DaoException;
import com.epam.esm.exception.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DaoExceptionHandler {
    private final HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

    @ExceptionHandler(DaoException.class)
    public final ResponseEntity<ExceptionResponse> handleRuntimeExceptions(DaoException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(e.getLocalizedMessage(), e.getErrorCode());
        exceptionResponse.setErrorCode(status.value() + e.getErrorCode());
        return new ResponseEntity<>(exceptionResponse, status);
    }
}
