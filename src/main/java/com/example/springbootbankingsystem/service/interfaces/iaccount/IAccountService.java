package com.example.springbootbankingsystem.service.interfaces.iaccount;

import com.example.springbootbankingsystem.dto.accountdto.CheckingDTO;
import com.example.springbootbankingsystem.dto.accountdto.CreditCardDTO;
import com.example.springbootbankingsystem.dto.accountdto.SavingsDTO;
import com.example.springbootbankingsystem.dto.accountdto.StudentCheckingDTO;
import com.example.springbootbankingsystem.model.accounttypes.Checking;
import com.example.springbootbankingsystem.model.accounttypes.CreditCard;
import com.example.springbootbankingsystem.model.accounttypes.Savings;
import com.example.springbootbankingsystem.model.accounttypes.StudentChecking;
import org.springframework.http.ResponseEntity;

public interface IAccountService {

    ResponseEntity<Savings> addNewSavingAccount(SavingsDTO savingsDTO);

    ResponseEntity<CreditCard> addNewCreditCard(CreditCardDTO creditCardDTO);

    ResponseEntity<?> addNewChecking(CheckingDTO checkingDTO);

    ResponseEntity<?> addNewStudentChecking(StudentCheckingDTO checkingDTO);
}
