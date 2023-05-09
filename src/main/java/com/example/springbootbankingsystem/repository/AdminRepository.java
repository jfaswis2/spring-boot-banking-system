package com.example.springbootbankingsystem.repository;

import com.example.springbootbankingsystem.model.usertypes.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
}
