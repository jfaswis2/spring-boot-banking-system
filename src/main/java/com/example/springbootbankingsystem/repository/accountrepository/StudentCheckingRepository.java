package com.example.springbootbankingsystem.repository.accountrepository;

import com.example.springbootbankingsystem.model.accounttypes.StudentChecking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentCheckingRepository extends JpaRepository<StudentChecking, Long> {
}
