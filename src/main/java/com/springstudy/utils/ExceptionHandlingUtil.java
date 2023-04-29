package com.springstudy.utils;

import com.springstudy.exceptions.entities.ApiError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ExceptionHandlingUtil {
    public static ApiError generateApiError(Exception ex, HttpServletRequest request, HttpStatus status) {
        return new ApiError(request.getRequestURI(), ex.getMessage(), status.value(), LocalDateTime.now());
    }
}
