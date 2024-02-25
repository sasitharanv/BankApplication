package com.example.BankApplication.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
@ControllerAdvice
public class GlobalExceptionHandler {

    public ResponseEntity<ErrorDetails> handleTodoAPIException(ToAPiException exception,
                                                               WebRequest webRequest) {

        ErrorDetails errorDetails=new ErrorDetails(
                LocalDateTime.now(),
                exception.getMesage(),
                webRequest.getDescription(false)
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
}
