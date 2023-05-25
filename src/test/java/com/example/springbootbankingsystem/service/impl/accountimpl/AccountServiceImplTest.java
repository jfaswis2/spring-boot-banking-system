package com.example.springbootbankingsystem.service.impl.accountimpl;

import com.example.springbootbankingsystem.dto.accountdto.SavingsDTO;
import com.example.springbootbankingsystem.dto.userdto.AccountHolderDTO;
import com.example.springbootbankingsystem.mapper.accountmapper.SavingsDTOMapper;
import com.example.springbootbankingsystem.mapper.usermapper.AccountHolderDTOMapper;
import com.example.springbootbankingsystem.model.accounttypes.Savings;
import com.example.springbootbankingsystem.model.usertypes.AccountHolder;
import com.example.springbootbankingsystem.repository.accountrepository.SavingsRepository;
import com.example.springbootbankingsystem.repository.userrepository.AccountHolderRepository;
import com.example.springbootbankingsystem.utils.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AccountServiceImplTest {

    @Mock
    private AccountHolderRepository accountHolderRepository;

    @Mock
    private SavingsDTOMapper savingsDTOMapper;

    @Mock
    private AccountHolderDTOMapper accountHolderDTOMapper;

    @Mock
    private SavingsRepository savingsRepository;

    @InjectMocks
    private AccountServiceImpl accountServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllPrimaryOwnerChecking() {
    }

    @Test
    void getAllSecondaryOwnerChecking() {
    }

    @Test
    void getCheckingAccount() {
    }

    @Test
    void addNewCheckingAccount() {
    }

    @Test
    void updateCheckingAccount() {
    }

    @Test
    void deleteCheckingAccount() {
    }

    @Test
    void getAllPrimaryOwnerStudentChecking() {
    }

    @Test
    void getAllSecondaryOwnerStudentChecking() {
    }

    @Test
    void getStudentCheckingAccount() {
    }

    @Test
    void addNewStudentCheckingAccount() {
    }

    @Test
    void updateStudentCheckingAccount() {
    }

    @Test
    void deleteStudentCheckingAccount() {
    }

    @Test
    void getAllPrimaryOwnerSavings() {
    }

    @Test
    void getAllSecondaryOwnerSavings() {
    }

    @Test
    void getSavingsAccount() {
    }

    @Test
    void addNewSavingAccountPrimaryOwnerAndSecondaryOwnerExists() {
        SavingsDTO savingsDTO = new SavingsDTO(
                1L,
                1L,
                BigDecimal.valueOf(10000L),
                "sdasda",
                BigDecimal.valueOf(0.4));

        Savings expectedSavings = new Savings();

        expectedSavings.setBalance(savingsDTO.balance());
        expectedSavings.setSecretKey(savingsDTO.secretKey());
        expectedSavings.setInterestRate(savingsDTO.interestRate());
        expectedSavings.setPenaltyFee(BigDecimal.valueOf(0.4));
        expectedSavings.setMinimumBalance(BigDecimal.valueOf(10000));
        expectedSavings.setStatus(Status.ACTIVE);
        expectedSavings.setCreatedDate(LocalDate.now());
        expectedSavings.setUpdateDate(LocalDate.now());
        expectedSavings.setDeleted(false);

        when(savingsDTOMapper.map(any(SavingsDTO.class))).thenReturn(expectedSavings);

        when(savingsRepository.save(any(Savings.class))).thenReturn(expectedSavings);

        when(accountHolderRepository.findById(1L)).thenReturn(Optional.of(new AccountHolder()));

        ResponseEntity<Savings> response = accountServiceImpl.addNewSavingAccount(savingsDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(expectedSavings, response.getBody());
        verify(savingsRepository, times(1)).save(any(Savings.class));
    }

    @Test
    void addNewSavingAccountPrimaryOwnerExistsAndSecondaryOwnerNull() {
        SavingsDTO savingsDTO = new SavingsDTO(
                1L,
                null,
                BigDecimal.valueOf(10000L),
                "sdasda",
                BigDecimal.valueOf(0.4));

        Savings expectedSavings = new Savings();

        expectedSavings.setBalance(savingsDTO.balance());
        expectedSavings.setSecretKey(savingsDTO.secretKey());
        expectedSavings.setInterestRate(savingsDTO.interestRate());
        expectedSavings.setPenaltyFee(BigDecimal.valueOf(0.4));
        expectedSavings.setMinimumBalance(BigDecimal.valueOf(10000));
        expectedSavings.setStatus(Status.ACTIVE);
        expectedSavings.setCreatedDate(LocalDate.now());
        expectedSavings.setUpdateDate(LocalDate.now());
        expectedSavings.setDeleted(false);

        when(savingsDTOMapper.map(any(SavingsDTO.class))).thenReturn(expectedSavings);

        when(savingsRepository.save(any(Savings.class))).thenReturn(expectedSavings);

        when(accountHolderRepository.findById(1L)).thenReturn(Optional.of(new AccountHolder()));

        ResponseEntity<Savings> response = accountServiceImpl.addNewSavingAccount(savingsDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(expectedSavings, response.getBody());
        assertNull(Objects.requireNonNull(response.getBody()).getSecondaryOwner());
        verify(savingsRepository, times(1)).save(any(Savings.class));
    }

    @Test
    void addNewSavingAccountPrimaryOwnerNonExiste() {
        SavingsDTO savingsDTO = new SavingsDTO(
                1L,
                null,
                BigDecimal.valueOf(10000L),
                "sdasda",
                BigDecimal.valueOf(0.4));

        when(accountHolderRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(IllegalStateException.class, () -> accountServiceImpl.addNewSavingAccount(savingsDTO));
        verify(savingsRepository, never()).save(any(Savings.class));
    }

    @Test
    void updateSavingsAccount() {
    }

    @Test
    void deleteSavingsAccount() {
    }

    @Test
    void getAllPrimaryOwnerCreditCard() {
    }

    @Test
    void getAllSecondaryOwnerCreditCard() {
    }

    @Test
    void getCreditCardAccount() {
    }

    @Test
    void addNewCreditCardAccount() {
    }

    @Test
    void updateCreditCardAccount() {
    }

    @Test
    void deleteCreditCardAccount() {
    }

    @Test
    void chargeMonthlyFee() {
    }
}