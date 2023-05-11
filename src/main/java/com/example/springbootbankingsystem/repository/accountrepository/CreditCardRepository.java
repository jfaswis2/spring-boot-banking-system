package com.example.springbootbankingsystem.repository.accountrepository;

import com.example.springbootbankingsystem.model.accounttypes.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {
}
