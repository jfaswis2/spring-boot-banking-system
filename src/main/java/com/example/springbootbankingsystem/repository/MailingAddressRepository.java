package com.example.springbootbankingsystem.repository;

import com.example.springbootbankingsystem.model.usertypes.MailingAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MailingAddressRepository extends JpaRepository<MailingAddress, Long> {
}
