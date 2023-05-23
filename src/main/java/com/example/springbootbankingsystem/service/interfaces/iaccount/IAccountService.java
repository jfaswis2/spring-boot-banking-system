package com.example.springbootbankingsystem.service.interfaces.iaccount;

import com.example.springbootbankingsystem.dto.accountdto.CheckingDTO;
import com.example.springbootbankingsystem.dto.accountdto.CreditCardDTO;
import com.example.springbootbankingsystem.dto.accountdto.SavingsDTO;
import com.example.springbootbankingsystem.dto.accountdto.StudentCheckingDTO;
import com.example.springbootbankingsystem.dto.userdto.AccountHolderDTO;
import com.example.springbootbankingsystem.model.accounttypes.*;
import com.example.springbootbankingsystem.model.usertypes.AccountHolder;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IAccountService {

    //--------------- CHECKING ----------------------
    ResponseEntity<List<Checking>> getAllPrimaryOwnerChecking(Long idAccountHolder);
    ResponseEntity<List<Checking>> getAllSecondaryOwnerChecking(Long idAccountHolder);
    ResponseEntity<Checking> getPrimaryOwnerChecking(Long idChecking);
    ResponseEntity<Checking> getSecondaryOwnerChecking(Long idChecking);
    ResponseEntity<?> addNewChecking(CheckingDTO checkingDTO);
    ResponseEntity<Checking> updateChecking(Checking checking);
    ResponseEntity<Void> deleteChecking(Long idAccountHolder, Long idChecking);


    //----------------- SAVINGS -------------------------

    ResponseEntity<List<Savings>> getAllPrimaryOwnerSavings(Long idAccountHolder);
    ResponseEntity<List<Savings>> getAllSecondaryOwnerSavings(Long idAccountHolder);
    ResponseEntity<Savings> getSavings(Long idSavings);
    ResponseEntity<Savings> addNewSavingAccount(SavingsDTO savingsDTO);
    ResponseEntity<Savings> updateSavings(Long id, Savings savings);
    ResponseEntity<Void> deleteSavings(Long idChecking);

/*
    ResponseEntity<Savings> getSavingAccount(Long idAccountHolder, Long idSavingAccount);

    ResponseEntity<List<Savings>> getAllSavingAccount(Long idAccountHolder);

    ResponseEntity<Savings> updateSavingAccount(Long idAccountHolder, Long idSavingAccount, AccountHolderDTO accountHolder);

    ResponseEntity<Void> deleteSavingAccount(Long idAccountHolder, Long idSavingAccount);

    //------------------- CREDIT-CARD --------------------------
    ResponseEntity<CreditCard> addNewCreditCard(CreditCardDTO creditCardDTO);

    //---------------------- CHECKING ---------------------------
    ResponseEntity<?> addNewChecking(CheckingDTO checkingDTO);

    //---------------------- STUDENT-CHECKING -----------------------
    ResponseEntity<?> addNewStudentChecking(StudentCheckingDTO checkingDTO);*/
}
