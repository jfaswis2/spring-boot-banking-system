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
    //--------------- ACCOUNT ----------------------
    ResponseEntity<List<Account>> getAllPrimaryOwnerAccount(Long idAccountHolder);
    ResponseEntity<List<Account>> getAllSecondaryOwnerAccount(Long idAccountHolder);
    ResponseEntity<Void> deleteAllAccount(Long idAccountHolder);
    ResponseEntity<Account> getAccount(Long idAccountHolder, Long idAccount);

    //----------------- SAVINGS -------------------------
    ResponseEntity<Savings> addNewSavingAccount(SavingsDTO savingsDTO);

    /*
    ResponseEntity<Savings> getSavingAccount(Long idAccountHolder, Long idSavingAccount);

    ResponseEntity<List<Savings>> getAllSavingAccount(Long idAccountHolder);

    ResponseEntity<Savings> updateSavingAccount(Long idAccountHolder, Long idSavingAccount, AccountHolderDTO accountHolder);

    ResponseEntity<Void> deleteSavingAccount(Long idAccountHolder, Long idSavingAccount);
    */

    //------------------- CREDIT-CARD --------------------------
    ResponseEntity<CreditCard> addNewCreditCard(CreditCardDTO creditCardDTO);

    //---------------------- CHECKING ---------------------------
    ResponseEntity<?> addNewChecking(CheckingDTO checkingDTO);

    //---------------------- STUDENT-CHECKING -----------------------
    ResponseEntity<?> addNewStudentChecking(StudentCheckingDTO checkingDTO);
}
