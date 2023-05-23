package com.example.springbootbankingsystem.controller.accountcontroller;

import com.example.springbootbankingsystem.dto.accountdto.CheckingDTO;
import com.example.springbootbankingsystem.dto.accountdto.CreditCardDTO;
import com.example.springbootbankingsystem.dto.accountdto.SavingsDTO;
import com.example.springbootbankingsystem.dto.accountdto.StudentCheckingDTO;
import com.example.springbootbankingsystem.model.accounttypes.*;
import com.example.springbootbankingsystem.service.impl.accountimpl.AccountServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/accounts")
public class AccountController {

    private final AccountServiceImpl accountService;



    //--------------------------- CHECKING -------------------------
    /*
    @GetMapping("/all/checking/primary/{id}")
    public ResponseEntity<List<Checking>> getAllPrimaryOwnerChecking(@PathVariable Long id) {
        return accountService.getAllPrimaryOwnerChecking(id);
    }

    @GetMapping("/all/checking/secondary/{id}")
    public ResponseEntity<List<Checking>> getAllSecondaryOwnerChecking(@PathVariable Long id) {
        return accountService.getAllSecondaryOwnerChecking(id);
    }*/


    //---------------------- SAVINGS -------------------------
    @GetMapping("/all/savings/primary/{id}")
    @Operation(summary = "Get all Primary Owner Savings")
    public ResponseEntity<List<Savings>> getAllPrimaryOwnerSavings(@PathVariable Long id) {
        return accountService.getAllPrimaryOwnerSavings(id);
    }

    @GetMapping("/all/savings/secondary/{id}")
    @Operation(summary = "Get all Secondary Owner Savings")
    public ResponseEntity<List<Savings>> getAllSecondaryOwnerSavings(@PathVariable Long id) {
        return accountService.getAllSecondaryOwnerSavings(id);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Savings")
    public ResponseEntity<Savings> getSavings(@PathVariable Long id) {
        return accountService.getSavings(id);
    }

    @PostMapping("/add/savings")
    @Operation(summary = "Add new Savings Account")
    public ResponseEntity<Savings> addNewSavingsAccount(@RequestBody SavingsDTO savingsDTO) {
        return accountService.addNewSavingAccount(savingsDTO);
    }

    @PutMapping("/update/savings/{id}")
    @Operation(summary = "Update Savings Account")
    public ResponseEntity<Savings> updateSavingsAccount(@RequestBody Savings savings, @PathVariable Long id) {
        return accountService.updateSavings(id, savings);
    }

    @DeleteMapping("/delete/savings/{id}")
    @Operation(summary = "Delete Savings Account")
    public ResponseEntity<Void> deleteSavingsAccount(@PathVariable Long id) {
        return accountService.deleteSavings(id);
    }


/*
    @GetMapping("/primary/{idAccountHolder}/{idAccount}")
    public ResponseEntity<Account> getPrimaryOwnerAccount(@PathVariable Long idAccountHolder, @PathVariable Long idAccount) {
        return accountService.getPrimaryOwnerAccount(idAccountHolder,idAccount);
    }

    @GetMapping("/secondary/{idAccountHolder}/{idAccount}")
    public ResponseEntity<Account> getSecondaryOwnerAccount(@PathVariable Long idAccountHolder, @PathVariable Long idAccount) {
        return accountService.getSecondaryOwnerAccount(idAccountHolder,idAccount);
    }

    @DeleteMapping("/delete/primary/{idAccountHolder}/{idAccount}")
    public ResponseEntity<Void> deletePrimaryOwnerAccount(@PathVariable Long idAccountHolder, @PathVariable Long idAccount) {
        return accountService.deletePrimaryOwnerAccount(idAccountHolder, idAccount);
    }

    @DeleteMapping("/delete/secondary/{idAccountHolder}/{idAccount}")
    public ResponseEntity<Void> deleteSecondaryOwnerAccount(@PathVariable Long idAccountHolder, @PathVariable Long idAccount) {
        return accountService.deleteSecondaryOwnerAccount(idAccountHolder, idAccount);
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
    }*/
}
