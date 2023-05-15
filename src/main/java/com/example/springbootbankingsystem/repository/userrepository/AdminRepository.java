package com.example.springbootbankingsystem.repository.userrepository;

import com.example.springbootbankingsystem.model.usertypes.AccountHolder;
import com.example.springbootbankingsystem.model.usertypes.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findAccountHolderByEmail(String email);
}
