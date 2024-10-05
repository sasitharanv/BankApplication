package com.example.BankApplication.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class GenerateOtpDto {
    public int accountNumber;
    public String NICNumber;
    public String phoneNumber;
    public String email;

}
