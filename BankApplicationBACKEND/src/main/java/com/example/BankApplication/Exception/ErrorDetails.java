package com.example.BankApplication.Exception;

import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
public class ErrorDetails {
    private LocalDateTime timestamp;
    private String message;

    private String Details;


}
