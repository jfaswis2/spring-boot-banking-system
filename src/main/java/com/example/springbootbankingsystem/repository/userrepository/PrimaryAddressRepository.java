package com.example.springbootbankingsystem.repository.userrepository;

import com.example.springbootbankingsystem.model.usertypes.PrimaryAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrimaryAddressRepository extends JpaRepository<PrimaryAddress, Long> {
}
