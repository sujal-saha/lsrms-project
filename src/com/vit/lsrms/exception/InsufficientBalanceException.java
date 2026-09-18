package com.vit.lsrms.exception;

// Custom exception when student balance is too low for fines or dues
public class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}
