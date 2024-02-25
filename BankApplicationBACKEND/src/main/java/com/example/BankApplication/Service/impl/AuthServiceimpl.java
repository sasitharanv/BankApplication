package com.example.BankApplication.Service.impl;
import com.example.BankApplication.Dto.LoginDto;
import com.example.BankApplication.Dto.RegisterDto;
import com.example.BankApplication.Exception.ToAPiException;
import com.example.BankApplication.Jwt.JwtTokenProvider;
import com.example.BankApplication.Model.Role;
import com.example.BankApplication.Model.User;
import com.example.BankApplication.Repository.RoleRepository;
import com.example.BankApplication.Repository.UserRepository;
import com.example.BankApplication.Service.AuthService;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Setter
@AllArgsConstructor

@Service
public class AuthServiceimpl implements AuthService {

    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private JwtTokenProvider jwtTokenProvider;


    @Override
    public String register(RegisterDto registerDto) {
        if(userRepository.existsByUsername(registerDto.getUsername())){
            throw  new ToAPiException(HttpStatus.BAD_REQUEST,"Username Already exists");
        }
        // check email is alreday exists
        if(userRepository.existsByEmail(registerDto.getEmail()))
        {
            throw new ToAPiException(HttpStatus.BAD_REQUEST,"The Email Already exists");
        }
        User user=new User();
        user.setName(registerDto.getName());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());

            Set<Role> roles=new HashSet<>();

            Role userRole=roleRepository.findByName("ROLE_USER");
            roles.add(userRole);
            user.setRoles(roles);
            userRepository.save(user);
        return "User Registered Successfully!!!";
    }


    @Override
    public String login(LoginDto loginDto) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(),
                loginDto.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);

        return token;
    }


}
