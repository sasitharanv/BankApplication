package com.example.BankApplication.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Customer  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private String email;
    private String NICNumber;

    @OneToMany(mappedBy = "customer")
    private List<Accounts> accounts;


}
