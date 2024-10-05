package com.example.BankApplication.Service;

import com.example.BankApplication.Dto.*;
import com.example.BankApplication.Model.User;
import org.antlr.v4.runtime.Token;
import org.springframework.security.core.userdetails.UserDetails;

public interface  AuthService {
    String register(RegisterDto registerDto);

    JwtAuthResponse login(LoginDto loginDto);

    UserDetailsDto getCurrentUserDetails();

    void changePassword(PasswordChangeDto passwordChangeDto) throws Exception;

    String generateOtpForRegistration(GenerateOtpDto generateOtpDto);
}
