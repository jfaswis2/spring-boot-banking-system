package com.example.springbootbankingsystem.controller.accountcontroller;

import com.example.springbootbankingsystem.dto.accountdto.CheckingDTO;
import com.example.springbootbankingsystem.dto.accountdto.CreditCardDTO;
import com.example.springbootbankingsystem.dto.accountdto.SavingsDTO;
import com.example.springbootbankingsystem.dto.accountdto.StudentCheckingDTO;
import com.example.springbootbankingsystem.model.accounttypes.*;
import com.example.springbootbankingsystem.service.impl.accountimpl.AccountServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/accounts")
public class AccountController {

    private final AccountServiceImpl accountService;


    @GetMapping("/{id}")
    public ResponseEntity<List<Account>> getAllPrimaryOwnerAccount(@PathVariable Long id) {
        return accountService.getAllPrimaryOwnerAccount(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Account>> getAllSecondaryOwnerAccount(@PathVariable Long id) {
        return accountService.getAllSecondaryOwnerAccount(id);
    }


    @PostMapping("/add/savings")
    public ResponseEntity<Savings> addNewSavingAccount(@RequestBody SavingsDTO savingsDTO) {
        return accountService.addNewSavingAccount(savingsDTO);
    }

    @PostMapping("/add/credit-card")
    public ResponseEntity<CreditCard> addNewCreditCard(@RequestBody CreditCardDTO creditCardDTO) {
        return accountService.addNewCreditCard(creditCardDTO);
    }

    @PostMapping("/add/checking")
    public ResponseEntity<?> addNewChecking(@RequestBody CheckingDTO checkingDTO) {
        return accountService.addNewChecking(checkingDTO);
    }

    @PostMapping("/add/student-checking")
    public ResponseEntity<?> addNewStudentChecking(@RequestBody StudentCheckingDTO studentCheckingDTO) {
        return accountService.addNewStudentChecking(studentCheckingDTO);
    }
}
