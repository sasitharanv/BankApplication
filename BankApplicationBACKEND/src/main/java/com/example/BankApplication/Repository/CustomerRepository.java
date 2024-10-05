package com.example.BankApplication.Repository;
import com.example.BankApplication.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {



    // Find Customer by email
    Optional<Customer> findByEmail(String email);

    // Find Customer by NICNumber
    Optional<Customer> findByNICNumber(String nicNumber);

    // Any other custom queries you need
}
