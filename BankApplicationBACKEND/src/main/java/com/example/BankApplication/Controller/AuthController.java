package com.example.BankApplication.Controller;

import com.example.BankApplication.Dto.JwtAuthResponse;
import com.example.BankApplication.Dto.LoginDto;
import com.example.BankApplication.Dto.RegisterDto;
import com.example.BankApplication.Service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthService authService;

    //Build Register new User Api

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody  RegisterDto registerDto){
        String response= authService.register(registerDto);
        return  new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    //Build login api
    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginDto loginDto){
        String token = authService.login(loginDto);

        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setAccessToken(token);


        return new ResponseEntity<>(jwtAuthResponse, HttpStatus.OK);
    }



}
