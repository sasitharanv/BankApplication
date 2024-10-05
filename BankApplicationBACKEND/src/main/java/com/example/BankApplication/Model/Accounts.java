package com.example.BankApplication.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Accounts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int accountNumber;
    private BigDecimal balance;
    private String accountType;

    @ManyToOne
    @JoinColumn(name = "customer_id") // Changed to 'customer_id'
    private Customer customer;
}
