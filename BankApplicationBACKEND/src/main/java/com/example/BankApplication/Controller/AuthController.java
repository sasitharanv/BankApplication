package com.example.BankApplication.Controller;

import com.example.BankApplication.Dto.JwtAuthResponse;
import com.example.BankApplication.Dto.LoginDto;
import com.example.BankApplication.Dto.RegisterDto;
import com.example.BankApplication.Dto.UserDto;
import com.example.BankApplication.Model.User;
import com.example.BankApplication.Repository.UserRepository;
import com.example.BankApplication.Service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")

@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthService authService;
    private UserRepository userRepository;
    //Build Register new User Api

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody  RegisterDto registerDto){
        String response= authService.register(registerDto);
        return  new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    //Build login api
    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginDto loginDto){
       JwtAuthResponse jwtAuthResponse = authService.login(loginDto);


        return new ResponseEntity<>(jwtAuthResponse, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/greeting")
    public String greeting() {

        return "Hello, this is a simple greeting from the server.";
    }


}
