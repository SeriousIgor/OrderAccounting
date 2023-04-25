package com.springstudy.exceptions.handlers;

import com.springstudy.utils.ExceptionHandlingUtil;
import jakarta.servlet.http.HttpServletRequest;
import javassist.NotFoundException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DefaultExceptionHandler {
    @ExceptionHandler(value = {ResourceNotFoundException.class})
    public ResponseEntity<Object> handleException(ResourceNotFoundException e, HttpServletRequest request) {
        return new ResponseEntity<>(
                ExceptionHandlingUtil.generateApiError(e, request, HttpStatus.FORBIDDEN),
                HttpStatus.FORBIDDEN
        );
    }

    @ExceptionHandler(value = {NullPointerException.class})
    public ResponseEntity<Object> handleException(NullPointerException e, HttpServletRequest request) {
        return new ResponseEntity<>(
                ExceptionHandlingUtil.generateApiError(e, request, HttpStatus.METHOD_FAILURE),
                HttpStatus.METHOD_FAILURE
        );
    }

    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<Object> handleException(NotFoundException e, HttpServletRequest request) {
        return new ResponseEntity<>(
                ExceptionHandlingUtil.generateApiError(e, request, HttpStatus.FORBIDDEN),
                HttpStatus.FORBIDDEN
        );
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleException(RuntimeException e, HttpServletRequest request) {
        System.out.println(e);
        return new ResponseEntity<>(
                ExceptionHandlingUtil.generateApiError(e, request, HttpStatus.INTERNAL_SERVER_ERROR),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleException(Exception e, HttpServletRequest request) {
        return new ResponseEntity<>(
                ExceptionHandlingUtil.generateApiError(e, request, HttpStatus.INTERNAL_SERVER_ERROR),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
