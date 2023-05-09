package com.example.springbootbankingsystem.repository.accountrepository;

import com.example.springbootbankingsystem.model.accounttypes.Savings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SavingsAccountRepository extends JpaRepository<Savings,Long> {
}
