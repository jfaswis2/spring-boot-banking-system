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


    //--------------------------- ACCOUNT -------------------------
    @GetMapping("/all/primary/{id}")
    public ResponseEntity<List<Account>> getAllPrimaryOwnerAccount(@PathVariable Long id) {
        return accountService.getAllPrimaryOwnerAccount(id);
    }

    @GetMapping("/all/secondary/{id}")
    public ResponseEntity<List<Account>> getAllSecondaryOwnerAccount(@PathVariable Long id) {
        return accountService.getAllSecondaryOwnerAccount(id);
    }

    @GetMapping("/primary/{idAccountHolder}/{idAccount}")
    public ResponseEntity<Account> getPrimaryOwnerAccount(@PathVariable Long idAccountHolder, @PathVariable Long idAccount) {
        return accountService.getPrimaryOwnerAccount(idAccountHolder,idAccount);
    }

    @GetMapping("/secondary/{idAccountHolder}/{idAccount}")
    public ResponseEntity<Account> getSecondaryOwnerAccount(@PathVariable Long idAccountHolder, @PathVariable Long idAccount) {
        return accountService.getSecondaryOwnerAccount(idAccountHolder,idAccount);
    }


    //---------------------- SAVING -----------------------
    @PostMapping("/add/savings")
    public ResponseEntity<Savings> addNewSavingAccount(@RequestBody SavingsDTO savingsDTO) {
        return accountService.addNewSavingAccount(savingsDTO);
    }

    //------------------------- CREDIT-CARD ------------------
    @PostMapping("/add/credit-card")
    public ResponseEntity<CreditCard> addNewCreditCard(@RequestBody CreditCardDTO creditCardDTO) {
        return accountService.addNewCreditCard(creditCardDTO);
    }

    //------------------------- CHECKING ------------------------
    @PostMapping("/add/checking")
    public ResponseEntity<?> addNewChecking(@RequestBody CheckingDTO checkingDTO) {
        return accountService.addNewChecking(checkingDTO);
    }

    //------------------------ STUDENT-CHECKING ----------------------
    @PostMapping("/add/student-checking")
    public ResponseEntity<?> addNewStudentChecking(@RequestBody StudentCheckingDTO studentCheckingDTO) {
        return accountService.addNewStudentChecking(studentCheckingDTO);
    }
}
