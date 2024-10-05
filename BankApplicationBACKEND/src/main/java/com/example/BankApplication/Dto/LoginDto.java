package com.example.BankApplication.Dto;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {
    private String usernameOrEmail;
    private String password;
}
