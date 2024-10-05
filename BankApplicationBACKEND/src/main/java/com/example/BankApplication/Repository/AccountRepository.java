package com.example.BankApplication.Repository;

import com.example.BankApplication.Model.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Accounts, Integer> {

    Optional<Accounts> findByAccountNumber(int accountNumber);
}
