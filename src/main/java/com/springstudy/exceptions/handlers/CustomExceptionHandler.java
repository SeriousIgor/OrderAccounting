package com.springstudy.exceptions.handlers;

import com.springstudy.exceptions.entities.DatabaseDataUpdateException;
import com.springstudy.exceptions.entities.EmptyDatabaseException;
import com.springstudy.utils.ExceptionHandlingUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

@ControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(value = {DatabaseDataUpdateException.class})
    public ResponseEntity<Object> handleException(DatabaseDataUpdateException e, HttpServletRequest request) {
        return new ResponseEntity<>(
                ExceptionHandlingUtil.generateApiError(e, request, HttpStatus.INTERNAL_SERVER_ERROR),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(value = {EmptyDatabaseException.class})
    public ResponseEntity<Object> handleException(EmptyDatabaseException e, HttpServletRequest request) {
        return new ResponseEntity<>(
                ExceptionHandlingUtil.generateApiError(e, request, HttpStatus.NOT_FOUND),
                HttpStatus.NOT_FOUND
        );
    }
}
