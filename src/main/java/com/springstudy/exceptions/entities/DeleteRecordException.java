package com.springstudy.exceptions.entities;

public class DeleteRecordException extends Exception {
    public DeleteRecordException(String message, Throwable throwable) {
        super(message, throwable);
    }
    public DeleteRecordException(String message) {
        super(message);
    }
}
