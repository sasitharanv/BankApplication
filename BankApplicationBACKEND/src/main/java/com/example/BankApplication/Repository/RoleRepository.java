package com.example.BankApplication.Repository;

import com.example.BankApplication.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Integer> {

        Role findByName(String name);
}
