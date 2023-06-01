package com.example.springbootbankingsystem.service.impl.accountimpl;

import com.example.springbootbankingsystem.dto.accountdto.SavingsDTO;
import com.example.springbootbankingsystem.dto.accountdto.StudentCheckingDTO;
import com.example.springbootbankingsystem.mapper.accountmapper.SavingsDTOMapper;
import com.example.springbootbankingsystem.mapper.accountmapper.StudentCheckingDTOMapper;
import com.example.springbootbankingsystem.mapper.usermapper.AccountHolderDTOMapper;
import com.example.springbootbankingsystem.model.accounttypes.Savings;
import com.example.springbootbankingsystem.model.accounttypes.StudentChecking;
import com.example.springbootbankingsystem.model.usertypes.AccountHolder;
import com.example.springbootbankingsystem.repository.accountrepository.SavingsRepository;
import com.example.springbootbankingsystem.repository.accountrepository.StudentCheckingRepository;
import com.example.springbootbankingsystem.repository.userrepository.AccountHolderRepository;
import com.example.springbootbankingsystem.utils.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AccountServiceImplTest {

    @Mock
    private AccountHolderRepository accountHolderRepository;

    @Mock
    private SavingsDTOMapper savingsDTOMapper;

    @Mock
    private SavingsRepository savingsRepository;

    @Mock
    private StudentCheckingDTOMapper studentCheckingDTOMapper;

    @Mock
    private StudentCheckingRepository studentCheckingRepository;

    @InjectMocks
    private AccountServiceImpl accountServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    //----------------------- CHECKING --------------------------
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

    //------------------------- STUDENT-CHECKING ----------------------
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
    void addNewStudentCheckingAccountPrimaryOwnerAndSecondaryOwnerExists() {
        StudentCheckingDTO studentCheckingDTO = new StudentCheckingDTO(
                1L,
                1L,
                "asdasdas",
                BigDecimal.valueOf(10000)
        );

        StudentChecking expectedStudentChecking = new StudentChecking();
        expectedStudentChecking.setSecretKey(studentCheckingDTO.secretKey());
        expectedStudentChecking.setBalance(studentCheckingDTO.balance());
        expectedStudentChecking.setPenaltyFee(BigDecimal.valueOf(40L));
        expectedStudentChecking.setStatus(Status.ACTIVE);
        expectedStudentChecking.setCreatedDate(LocalDate.now());
        expectedStudentChecking.setUpdateDate(LocalDate.now());
        expectedStudentChecking.setDeleted(false);

        AccountHolder accountHolder = new AccountHolder();
        accountHolder.setDateOfBirth(LocalDate.of(2010, 1, 1));

        when(studentCheckingDTOMapper.map(any(StudentCheckingDTO.class))).thenReturn(expectedStudentChecking);
        when(accountHolderRepository.findById(1L)).thenReturn(Optional.of(accountHolder));
        when(studentCheckingRepository.save(any(StudentChecking.class))).thenReturn(expectedStudentChecking);

        ResponseEntity<?> response = accountServiceImpl.addNewStudentCheckingAccount(studentCheckingDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(expectedStudentChecking, response.getBody());
        verify(studentCheckingRepository, times(1)).save(any(StudentChecking.class));
    }

    @Test
    void addNewStudentCheckingAccountPrimaryOwnerExistAndSecondaryOwnerNull() {
        StudentCheckingDTO studentCheckingDTO = new StudentCheckingDTO(
                1L,
                null,
                "asdasdas",
                BigDecimal.valueOf(10000)
        );

        StudentChecking expectedStudentChecking = new StudentChecking();
        expectedStudentChecking.setSecretKey(studentCheckingDTO.secretKey());
        expectedStudentChecking.setBalance(studentCheckingDTO.balance());
        expectedStudentChecking.setPenaltyFee(BigDecimal.valueOf(40L));
        expectedStudentChecking.setStatus(Status.ACTIVE);
        expectedStudentChecking.setCreatedDate(LocalDate.now());
        expectedStudentChecking.setUpdateDate(LocalDate.now());
        expectedStudentChecking.setDeleted(false);

        AccountHolder accountHolder = new AccountHolder();
        accountHolder.setDateOfBirth(LocalDate.of(2010, 1, 1));

        when(studentCheckingDTOMapper.map(any(StudentCheckingDTO.class))).thenReturn(expectedStudentChecking);
        when(accountHolderRepository.findById(1L)).thenReturn(Optional.of(accountHolder));
        when(studentCheckingRepository.save(any(StudentChecking.class))).thenReturn(expectedStudentChecking);

        ResponseEntity<?> response = accountServiceImpl.addNewStudentCheckingAccount(studentCheckingDTO);

        StudentChecking studentChecking = (StudentChecking) response.getBody();

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(expectedStudentChecking, response.getBody());
        assertNull(studentChecking.getSecondaryOwner());
        verify(studentCheckingRepository, times(1)).save(any(StudentChecking.class));
    }

    @Test
    void addNewStudentCheckingAccountPrimaryOwnerNonExist() {
        StudentCheckingDTO studentCheckingDTO = new StudentCheckingDTO(
                1L,
                null,
                "asdasdas",
                BigDecimal.valueOf(10000)
        );

        when(accountHolderRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(IllegalStateException.class, () -> accountServiceImpl.addNewStudentCheckingAccount(studentCheckingDTO));
        verify(studentCheckingRepository, never()).save(any(StudentChecking.class));
    }

    @Test
    void addNewStudentCheckingAccountPrimaryOwnerNonStudent() {
        StudentCheckingDTO studentCheckingDTO = new StudentCheckingDTO(
                1L,
                1L,
                "asdasdas",
                BigDecimal.valueOf(10000)
        );

        StudentChecking expectedStudentChecking = new StudentChecking();
        expectedStudentChecking.setSecretKey(studentCheckingDTO.secretKey());
        expectedStudentChecking.setBalance(studentCheckingDTO.balance());
        expectedStudentChecking.setPenaltyFee(BigDecimal.valueOf(40L));
        expectedStudentChecking.setStatus(Status.ACTIVE);
        expectedStudentChecking.setCreatedDate(LocalDate.now());
        expectedStudentChecking.setUpdateDate(LocalDate.now());
        expectedStudentChecking.setDeleted(false);

        AccountHolder accountHolder = new AccountHolder();
        accountHolder.setDateOfBirth(LocalDate.of(1997, 3, 2));

        when(studentCheckingDTOMapper.map(any(StudentCheckingDTO.class))).thenReturn(expectedStudentChecking);
        when(accountHolderRepository.findById(1L)).thenReturn(Optional.of(accountHolder));
        when(studentCheckingRepository.save(any(StudentChecking.class))).thenReturn(expectedStudentChecking);

        assertThrows(IllegalStateException.class, () -> accountServiceImpl.addNewStudentCheckingAccount(studentCheckingDTO));
        verify(studentCheckingRepository, never()).save(any(StudentChecking.class));
    }

    @Test
    void updateStudentCheckingAccount() {
    }

    @Test
    void deleteStudentCheckingAccount() {
    }

    //------------------- SAVINGS -----------------------------
    @Test
    void getAllPrimaryOwnerSavingsExistingId() {

        Long accountHolderId = 1L;
        AccountHolder accountHolder = new AccountHolder();
        accountHolder.setId(accountHolderId);
        List<Savings> savingsList = new ArrayList<>();
        Savings savings1 = new Savings();
        savings1.setId(1L);
        savingsList.add(savings1);
        Savings savings2 = new Savings();
        savings2.setId(2L);
        savingsList.add(savings2);
        accountHolder.setPrimaryOwnerSavingsList(savingsList);

        when(accountHolderRepository.findById(accountHolderId)).thenReturn(Optional.of(accountHolder));

        ResponseEntity<List<Savings>> response = accountServiceImpl.getAllPrimaryOwnerSavings(accountHolderId);

        assertEquals(HttpStatus.FOUND, response.getStatusCode());
        List<Savings> resultSavingsList = response.getBody();
        assertEquals(2, resultSavingsList.size());
        assertEquals(1L, resultSavingsList.get(0).getId());
        assertEquals(2L, resultSavingsList.get(1).getId());

        verify(accountHolderRepository, times(1)).findById(accountHolderId);
    }

    @Test
    void getAllPrimaryOwnerSavingsNonExistingId() {
        Long idAccountHolder = 1L;
        when(accountHolderRepository.findById(idAccountHolder)).thenReturn(Optional.empty());

        assertThrows(IllegalStateException.class, () -> {
            accountServiceImpl.getAllPrimaryOwnerSavings(idAccountHolder);
        });
        verify(accountHolderRepository, times(1)).findById(idAccountHolder);
    }

    @Test
    void getAllSecondaryOwnerSavingsExistingId() {

        Long accountHolderId = 1L;
        AccountHolder accountHolder = new AccountHolder();
        accountHolder.setId(accountHolderId);
        List<Savings> savingsList = new ArrayList<>();
        Savings savings1 = new Savings();
        savings1.setId(1L);
        savingsList.add(savings1);
        Savings savings2 = new Savings();
        savings2.setId(2L);
        savingsList.add(savings2);
        accountHolder.setSecondaryOwnerSavingsList(savingsList);

        when(accountHolderRepository.findById(accountHolderId)).thenReturn(Optional.of(accountHolder));

        ResponseEntity<List<Savings>> response = accountServiceImpl.getAllSecondaryOwnerSavings(accountHolderId);

        assertEquals(HttpStatus.FOUND, response.getStatusCode());
        List<Savings> resultSavingsList = response.getBody();
        assertEquals(2, resultSavingsList.size());
        assertEquals(1L, resultSavingsList.get(0).getId());
        assertEquals(2L, resultSavingsList.get(1).getId());

        verify(accountHolderRepository, times(1)).findById(accountHolderId);
    }

    @Test
    void getAllSecondaryOwnerSavingsNonExistingId() {
        Long idAccountHolder = 1L;
        when(accountHolderRepository.findById(idAccountHolder)).thenReturn(Optional.empty());

        assertThrows(IllegalStateException.class, () -> {
            accountServiceImpl.getAllSecondaryOwnerSavings(idAccountHolder);
        });
        verify(accountHolderRepository, times(1)).findById(idAccountHolder);
    }

    @Test
    void getSavingsAccountExistingId() {
        // Arrange
        Long idSavings = 1L;
        Savings savings = new Savings();
        savings.setId(idSavings);

        when(savingsRepository.findById(idSavings)).thenReturn(Optional.of(savings));

        // Act
        ResponseEntity<Savings> response = accountServiceImpl.getSavingsAccount(idSavings);

        // Assert
        assertEquals(HttpStatus.FOUND, response.getStatusCode());
        assertEquals(savings, response.getBody());
        verify(savingsRepository, times(1)).findById(idSavings);
    }

    @Test
    void getSavingsAccountNonExistingId() {
        // Arrange
        Long idSavings = 1L;

        when(savingsRepository.findById(idSavings)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(IllegalStateException.class, () -> {
            accountServiceImpl.getSavingsAccount(idSavings);
        });
        verify(savingsRepository, times(1)).findById(idSavings);
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
    void updateSavingsAccountExistingId() {

        Long id = 1L;
        Savings existingSavings = new Savings();
        existingSavings.setId(id);
        existingSavings.setBalance(BigDecimal.valueOf(1000));
        existingSavings.setSecretKey("oldSecretKey");
        existingSavings.setStatus(Status.ACTIVE);
        existingSavings.setPenaltyFee(BigDecimal.valueOf(50));
        existingSavings.setCreatedDate(LocalDate.now());
        existingSavings.setUpdateDate(LocalDate.now());
        existingSavings.setDeleted(false);
        existingSavings.setMinimumBalance(BigDecimal.valueOf(1000));
        existingSavings.setInterestRate(BigDecimal.valueOf(0.0025));

        Savings updatedSavings = new Savings();
        updatedSavings.setId(id);
        updatedSavings.setBalance(BigDecimal.valueOf(1500));
        updatedSavings.setSecretKey("newSecretKey");
        updatedSavings.setStatus(Status.FROZEN);
        updatedSavings.setPenaltyFee(BigDecimal.valueOf(75));
        updatedSavings.setCreatedDate(LocalDate.now().minusDays(1));
        updatedSavings.setUpdateDate(LocalDate.now().minusDays(1));
        updatedSavings.setDeleted(true);
        updatedSavings.setMinimumBalance(BigDecimal.valueOf(1000));
        updatedSavings.setInterestRate(BigDecimal.valueOf(0.005));

        when(savingsRepository.findById(id)).thenReturn(Optional.of(existingSavings));
        when(savingsRepository.save(existingSavings)).thenReturn(updatedSavings);

        ResponseEntity<Savings> response = accountServiceImpl.updateSavingsAccount(id, updatedSavings);

        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        assertEquals(updatedSavings, response.getBody());
        verify(savingsRepository, times(1)).findById(id);
        verify(savingsRepository, times(1)).save(existingSavings);
    }

    @Test
    void updateSavingsAccountNonExistingId() {
        Long id = 1L;
        Savings updatedSavings = new Savings();
        updatedSavings.setId(id);

        when(savingsRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(IllegalStateException.class, () -> {
            accountServiceImpl.updateSavingsAccount(id, updatedSavings);
        });
        verify(savingsRepository, times(1)).findById(id);
        verify(savingsRepository, times(0)).save(any());
    }

    @Test
    void deleteSavingsAccountExistingId() {
        Long idChecking = 1L;

        when(savingsRepository.findById(idChecking)).thenReturn(Optional.of(new Savings()));

        ResponseEntity<Void> response = accountServiceImpl.deleteSavingsAccount(idChecking);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(savingsRepository, times(1)).findById(idChecking);
        verify(savingsRepository, times(1)).deleteById(idChecking);
    }

    @Test
    void deleteSavingsAccountNonExistingId() {
        Long idChecking = 1L;

        when(savingsRepository.findById(idChecking)).thenReturn(Optional.empty());

        assertThrows(IllegalStateException.class, () -> {
            accountServiceImpl.deleteSavingsAccount(idChecking);
        });
        verify(savingsRepository, times(1)).findById(idChecking);
        verify(savingsRepository, times(0)).deleteById(any());
    }

    //---------------------- CREDIT-CARD ------------------------
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