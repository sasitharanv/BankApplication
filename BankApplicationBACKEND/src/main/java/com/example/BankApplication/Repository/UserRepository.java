package com.example.BankApplication.Repository;

import com.example.BankApplication.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {


    User findByUsername(String username);
    User findByEmail(String email);

    Boolean existsByEmail(String email);

    Optional<User> findByUsernameOrEmail(String username,String email);

    Boolean existsByUsername(String username);
}
