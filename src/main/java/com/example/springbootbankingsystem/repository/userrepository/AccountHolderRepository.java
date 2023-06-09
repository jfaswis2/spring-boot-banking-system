package com.example.springbootbankingsystem.repository.userrepository;

import com.example.springbootbankingsystem.model.accounttypes.Account;
import com.example.springbootbankingsystem.model.usertypes.AccountHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountHolderRepository extends JpaRepository<AccountHolder, Long> {

    Optional<AccountHolder> findAccountHolderByEmail(String email);

    @Query("SELECT a FROM AccountHolder a WHERE a.deleted = false")
    List<AccountHolder> findAllAccountHolder();
}
