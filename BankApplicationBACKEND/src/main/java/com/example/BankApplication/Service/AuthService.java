package com.example.BankApplication.Service;

import com.example.BankApplication.Dto.LoginDto;
import com.example.BankApplication.Dto.RegisterDto;

public interface  AuthService {
    String register(RegisterDto registerDto);

    String login(LoginDto loginDto);
}
