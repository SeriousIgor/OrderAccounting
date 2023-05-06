package com.springstudy.exceptions.handlers;

import com.springstudy.utils.ExceptionHandlingUtil;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {ExpiredJwtException.class, DisabledException.class, ServletException.class, IOException.class})
    public ResponseEntity<Object> handleException(Exception e, HttpServletRequest request) {
        return new ResponseEntity<>(
                ExceptionHandlingUtil.generateApiError(e, request, HttpStatus.FORBIDDEN),
                HttpStatus.FORBIDDEN
        );
    }

    @ExceptionHandler(value = {BadCredentialsException.class})
    public ResponseEntity<Object> handleException(BadCredentialsException e, HttpServletRequest request) {
        return new ResponseEntity<>(
                ExceptionHandlingUtil.generateApiError(e, request, HttpStatus.FORBIDDEN),
                HttpStatus.FORBIDDEN
        );
    }
}
