package com.vit.lsrms.exception;

// Custom exception for handling book search failures
public class BookNotFoundException extends Exception {
    public BookNotFoundException(String message) {
        super(message);
    }
}
