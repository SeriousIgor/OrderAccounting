package com.springstudy.exceptions.entities;

import java.time.LocalDateTime;

public record ApiError(
        String path,
        String message,
        int statusCode,
        LocalDateTime time
) {
}
