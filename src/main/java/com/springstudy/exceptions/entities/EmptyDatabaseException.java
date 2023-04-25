package com.springstudy.exceptions.entities;

public class EmptyDatabaseException extends Exception{
    public EmptyDatabaseException(String message, Throwable throwable) {
        super(message, throwable);
    }
    public EmptyDatabaseException(String message) {
        super(message);
    }
}
