package com.example.BankApplication.Service.impl;
import com.example.BankApplication.Dto.JwtAuthResponse;
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
import org.antlr.v4.runtime.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
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

            Role userRole=roleRepository.findByName("USER");
            roles.add(userRole);
            user.setRoles(roles);
            userRepository.save(user);
        return "User Registered Successfully!!!";
    }


    @Override
    public JwtAuthResponse login(LoginDto loginDto) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(),
                loginDto.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);
       Optional<User> userOptional= userRepository.findByUsernameOrEmail(loginDto.getUsernameOrEmail(),loginDto.getUsernameOrEmail());

       String role=null;
       if(userOptional.isPresent()){
    User loggedInUser=userOptional.get();
    Optional<Role> optionalrole= loggedInUser.getRoles().stream().findFirst();

    if(optionalrole.isPresent()){
        Role userRole=optionalrole.get();
        role=userRole.getName();
    }
}
       JwtAuthResponse response=new JwtAuthResponse();
       response.setRole(role);
       response.setAccessToken(token);


        return response;
    }

    @Override
    public User userDetails(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }


}
