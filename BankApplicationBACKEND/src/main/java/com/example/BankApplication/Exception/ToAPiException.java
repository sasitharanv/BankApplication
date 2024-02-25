package com.example.BankApplication.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter

public class ToAPiException  extends  RuntimeException{
    private HttpStatus status;
    private  String mesage;
}
