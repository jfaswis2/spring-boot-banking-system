package com.example.springbootbankingsystem.repository.accountrepository;

import com.example.springbootbankingsystem.model.accounttypes.Checking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckingRepository extends JpaRepository<Checking, Long> {
}
