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


    //-------------------------- CHECKING ----------------------------------
    @GetMapping("/all/checking/primary/{id}")
    @Operation(summary = "Get all Primary Owner Checking")
    public ResponseEntity<List<Checking>> getAllPrimaryOwnerChecking(@PathVariable Long id) {
        return accountService.getAllPrimaryOwnerChecking(id);
    }

    @GetMapping("/all/checking/secondary/{id}")
    @Operation(summary = "Get all Secondary Owner Checking")
    public ResponseEntity<List<Checking>> getAllSecondaryOwnerChecking(@PathVariable Long id) {
        return accountService.getAllSecondaryOwnerChecking(id);
    }

    @GetMapping("/checking/{id}")
    @Operation(summary = "Get Checking")
    public ResponseEntity<Checking> getCheckingAccount(@PathVariable Long id) {
        return accountService.getCheckingAccount(id);
    }

    @PostMapping("/add/checking")
    @Operation(summary = "Add new Checking Account")
    public ResponseEntity<?> addNewCheckingAccount(@RequestBody CheckingDTO checkingDTO) {
        return accountService.addNewCheckingAccount(checkingDTO);
    }

    @PutMapping("/update/checking/{id}")
    @Operation(summary = "Update Checking Account")
    public ResponseEntity<Checking> updateCheckingAccount(@RequestBody Checking checking,
                                                                        @PathVariable Long id) {
        return accountService.updateCheckingAccount(id, checking);
    }

    @DeleteMapping("/delete/checking/{id}")
    @Operation(summary = "Delete Checking Account")
    public ResponseEntity<Void> deleteCheckingAccount(@PathVariable Long id) {
        return accountService.deleteCheckingAccount(id);
    }

    //------------------------ STUDENT-CHECKING ----------------------
    @GetMapping("/all/student-checking/primary/{id}")
    @Operation(summary = "Get all Primary Owner Student Checking")
    public ResponseEntity<List<StudentChecking>> getAllPrimaryOwnerStudentChecking(@PathVariable Long id) {
        return accountService.getAllPrimaryOwnerStudentChecking(id);
    }

    @GetMapping("/all/student-checking/secondary/{id}")
    @Operation(summary = "Get all Secondary Owner Student Checking")
    public ResponseEntity<List<StudentChecking>> getAllSecondaryOwnerStudentChecking(@PathVariable Long id) {
        return accountService.getAllSecondaryOwnerStudentChecking(id);
    }

    @GetMapping("/student-checking/{id}")
    @Operation(summary = "Get Student Checking")
    public ResponseEntity<StudentChecking> getStudentCheckingAccount(@PathVariable Long id) {
        return accountService.getStudentCheckingAccount(id);
    }

    @PostMapping("/add/student-checking")
    @Operation(summary = "Add new Student Checking Account")
    public ResponseEntity<?> addNewStudentCheckingAccount(@RequestBody StudentCheckingDTO studentCheckingDTO) {
        return accountService.addNewStudentCheckingAccount(studentCheckingDTO);
    }

    @PutMapping("/update/student-checking/{id}")
    @Operation(summary = "Update Student Checking Account")
    public ResponseEntity<StudentChecking> updateStudentCheckingAccount(@RequestBody StudentChecking studentChecking,
                                                                        @PathVariable Long id) {
        return accountService.updateStudentCheckingAccount(id, studentChecking);
    }

    @DeleteMapping("/delete/student-checking/{id}")
    @Operation(summary = "Delete Student Checking Account")
    public ResponseEntity<Void> deleteStudentCheckingAccount(@PathVariable Long id) {
        return accountService.deleteStudentCheckingAccount(id);
    }

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

    @GetMapping("/savings/{id}")
    @Operation(summary = "Get Savings")
    public ResponseEntity<Savings> getSavings(@PathVariable Long id) {
        return accountService.getSavingsAccount(id);
    }

    @PostMapping("/add/savings")
    @Operation(summary = "Add new Savings Account")
    public ResponseEntity<Savings> addNewSavingsAccount(@RequestBody SavingsDTO savingsDTO) {
        return accountService.addNewSavingAccount(savingsDTO);
    }

    @PutMapping("/update/savings/{id}")
    @Operation(summary = "Update Savings Account")
    public ResponseEntity<Savings> updateSavings(@RequestBody Savings savings, @PathVariable Long id) {
        return accountService.updateSavingsAccount(id, savings);
    }

    @DeleteMapping("/delete/savings/{id}")
    @Operation(summary = "Delete Savings Account")
    public ResponseEntity<Void> deleteSavings(@PathVariable Long id) {
        return accountService.deleteSavingsAccount(id);
    }


    //------------------------ CREDIT-CARD -----------------------------

    @GetMapping("/all/credit-card/primary/{id}")
    @Operation(summary = "Get all Primary Owner Credit-Card")
    public ResponseEntity<List<CreditCard>> getAllPrimaryOwnerCreditCard(@PathVariable Long id) {
        return accountService.getAllPrimaryOwnerCreditCard(id);
    }

    @GetMapping("/all/credit-card/secondary/{id}")
    @Operation(summary = "Get all Secondary Owner Credit-Card")
    public ResponseEntity<List<CreditCard>> getAllSecondaryOwnerCreditCard(@PathVariable Long id) {
        return accountService.getAllSecondaryOwnerCreditCard(id);
    }

    @GetMapping("/credit-card/{id}")
    @Operation(summary = "Get Credit-Card")
    public ResponseEntity<CreditCard> getCreditCard(@PathVariable Long id) {
        return accountService.getCreditCardAccount(id);
    }

    @PostMapping("/add/credit-card")
    @Operation(summary = "Add new Credit-Card Account")
    public ResponseEntity<CreditCard> addNewCreditCardAccount(@RequestBody CreditCardDTO creditCardDTO) {
        return accountService.addNewCreditCardAccount(creditCardDTO);
    }

    @PutMapping("/update/credit-card/{id}")
    @Operation(summary = "Update Credit-Card Account")
    public ResponseEntity<CreditCard> updateCreditCardAccount(@RequestBody CreditCard creditCard, @PathVariable Long id) {
        return accountService.updateCreditCardAccount(id, creditCard);
    }

    @DeleteMapping("/delete/credit-card/{id}")
    @Operation(summary = "Delete Credit-Card Account")
    public ResponseEntity<Void> deleteCreditCardAccount(@PathVariable Long id) {
        return accountService.deleteCreditCardAccount(id);
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
