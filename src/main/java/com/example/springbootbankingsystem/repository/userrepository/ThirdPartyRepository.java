package com.example.springbootbankingsystem.repository.userrepository;

import com.example.springbootbankingsystem.model.usertypes.ThirdParty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThirdPartyRepository extends JpaRepository<ThirdParty, Long> {
}
