package com.example.BankApplication.Exception;

public class EmailNotCorrectException extends RuntimeException {

    public EmailNotCorrectException(String message) {
        super(message);
    }

}
