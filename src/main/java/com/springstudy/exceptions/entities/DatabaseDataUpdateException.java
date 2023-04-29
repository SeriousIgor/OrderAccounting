package com.springstudy.exceptions.entities;

public class DatabaseDataUpdateException extends Exception {
    public DatabaseDataUpdateException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public DatabaseDataUpdateException(String message) {
        super(message);
    }
}
