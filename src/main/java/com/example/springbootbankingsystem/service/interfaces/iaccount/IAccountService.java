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
    ResponseEntity<Void> deleteChecking(Long idChecking);

    //---------------------- STUDENT-CHECKING -----------------------
    ResponseEntity<List<StudentChecking>> getAllPrimaryOwnerStudentChecking(Long idAccountHolder);
    ResponseEntity<List<StudentChecking>> getAllSecondaryOwnerStudentChecking(Long idAccountHolder);
    ResponseEntity<StudentChecking> getStudentCheckingAccount(Long idStudentChecking);
    ResponseEntity<?> addNewStudentCheckingAccount(StudentCheckingDTO checkingDTO);
    ResponseEntity<StudentChecking> updateStudentCheckingAccount(Long id, StudentChecking studentChecking);
    ResponseEntity<Void> deleteStudentCheckingAccount(Long idStudentChecking);

    //----------------- SAVINGS -------------------------
    ResponseEntity<List<Savings>> getAllPrimaryOwnerSavings(Long idAccountHolder);
    ResponseEntity<List<Savings>> getAllSecondaryOwnerSavings(Long idAccountHolder);
    ResponseEntity<Savings> getSavingsAccount(Long idSavings);
    ResponseEntity<Savings> addNewSavingAccount(SavingsDTO savingsDTO);
    ResponseEntity<Savings> updateSavingsAccount(Long id, Savings savings);
    ResponseEntity<Void> deleteSavingsAccount(Long idChecking);

    //------------------- CREDIT-CARD --------------------------
    ResponseEntity<List<CreditCard>> getAllPrimaryOwnerCreditCard(Long idAccountHolder);
    ResponseEntity<List<CreditCard>> getAllSecondaryOwnerCreditCard(Long idAccountHolder);
    ResponseEntity<CreditCard> getCreditCardAccount(Long idCreditCard);
    ResponseEntity<CreditCard> addNewCreditCardAccount(CreditCardDTO creditCardDTO);
    ResponseEntity<CreditCard> updateCreditCardAccount(Long id, CreditCard creditCard);
    ResponseEntity<Void> deleteCreditCardAccount(Long idCreditCard);
}
