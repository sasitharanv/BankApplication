package com.example.BankApplication.Service.impl;
import com.example.BankApplication.Dto.*;
import com.example.BankApplication.Exception.InvalidOtpException;
import com.example.BankApplication.Exception.NICNotCorrectException;
import com.example.BankApplication.Exception.ToAPiException;
import com.example.BankApplication.Jwt.JwtTokenProvider;
import com.example.BankApplication.Model.*;
import com.example.BankApplication.Repository.AccountRepository;
import com.example.BankApplication.Repository.CustomerRepository;
import com.example.BankApplication.Repository.RoleRepository;
import com.example.BankApplication.Repository.UserRepository;
import com.example.BankApplication.Service.AuthService;
import com.example.BankApplication.Service.EmailService;
import com.example.BankApplication.Service.OtpService;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Setter
@AllArgsConstructor

@Service
public class AuthServiceimpl implements AuthService {

    private final AccountRepository accountRepository;
    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private JwtTokenProvider jwtTokenProvider;
    private CustomerRepository customerRepository;
    private EmailService emailService;
    private OtpService otpService;

    public String generateOtpForRegistration(GenerateOtpDto generateOtpDto) {
        // Find Customer by email
        Optional<Customer> customerOptional = customerRepository.findByEmail(generateOtpDto.getEmail());

        // Unwrap the Optional or throw an exception if the customer is not found
        Customer customer = customerOptional.orElseThrow(() ->
                new RuntimeException("The user not found"));

        // Use equals() for string comparison instead of '=='
        if (!customer.getNICNumber().equals(generateOtpDto.getNICNumber())) {
            throw new NICNotCorrectException("NIC number for the Customer is not Correct");
        }

        // Find the account by account number
        Optional<Accounts> accountsOptional = accountRepository.findByAccountNumber(generateOtpDto.accountNumber);

        // Unwrap the Optional or throw an exception if the account is not found
        Accounts accounts = accountsOptional.orElseThrow(() ->
                new RuntimeException("The Account Number is Entered is Not Found"));

        if(accounts.getCustomer().getId()!= customer.getId()){
            throw new RuntimeException("The Account Number is Entered is Not Incorrect");

        }

        String otp = otpService.generateOtp(customer.getEmail());
        Email email = new Email();
        email.setRecipients(customer.getEmail());
        email.setMessagebody(otp);
        email.setSubject("OTP for Account Registration");
        email.setAttachment("");

        emailService.sendsimplemail(email);

        return "OTP sent Successfully!!!";
    }



    @Override
    public String register(RegisterDto registerDto) {
        if(userRepository.existsByUsername(registerDto.getUsername())){
            throw  new ToAPiException(HttpStatus.BAD_REQUEST,"Username Already exists");
        }
        if(userRepository.existsByEmail(registerDto.getEmail()))
        {
            throw new ToAPiException(HttpStatus.BAD_REQUEST,"The Email Already exists");
        }

        boolean isValidOtp = otpService.validateOtp(registerDto.getEmail(), registerDto.getOtp());
        if (!isValidOtp) {
            throw new InvalidOtpException("The Account Number is Entered is Not Incorrect");
        }
        User user=new User();

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
    public UserDetailsDto getCurrentUserDetails() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException("User Not Found: " + email);
        }

        UserDetailsDto userDetailsDto = new UserDetailsDto(
                user.getUsername(), user.getEmail(),user.getRoles());

        return userDetailsDto;
    }


    @Override
    public void changePassword(PasswordChangeDto passwordChangeDto) throws  Exception{
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String email=authentication.getName();

        User user=userRepository.findByEmail(email);

        if(!passwordEncoder.matches(passwordChangeDto.getOldPassword(),user.getPassword())){
            throw new Exception("Current Password is incorrect");
        }

        user.setPassword(passwordEncoder.encode(passwordChangeDto.getNewPassword()));
        userRepository.save(user);

    }
}
