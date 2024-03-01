package com.example.BankApplication.Dto;

import com.example.BankApplication.Model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsDto {
    private String username;
    private String email;
    private Set<Role> roles;




}
