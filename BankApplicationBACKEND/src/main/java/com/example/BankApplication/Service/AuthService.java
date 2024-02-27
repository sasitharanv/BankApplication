package com.example.BankApplication.Service;

import com.example.BankApplication.Dto.JwtAuthResponse;
import com.example.BankApplication.Dto.LoginDto;
import com.example.BankApplication.Dto.RegisterDto;
import com.example.BankApplication.Model.User;
import org.antlr.v4.runtime.Token;

public interface  AuthService {
    String register(RegisterDto registerDto);

    JwtAuthResponse login(LoginDto loginDto);

    User userDetails(String username);
}
