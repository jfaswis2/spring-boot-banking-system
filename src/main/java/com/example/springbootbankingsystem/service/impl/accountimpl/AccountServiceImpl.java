package com.example.springbootbankingsystem.service.impl.accountimpl;

import com.example.springbootbankingsystem.dto.accountdto.CheckingDTO;
import com.example.springbootbankingsystem.dto.accountdto.SavingsDTO;
import com.example.springbootbankingsystem.mapper.accountmapper.CheckingDTOMapper;
import com.example.springbootbankingsystem.mapper.accountmapper.CreditCardDTOMapper;
import com.example.springbootbankingsystem.mapper.accountmapper.SavingsDTOMapper;
import com.example.springbootbankingsystem.mapper.accountmapper.StudentCheckingDTOMapper;
import com.example.springbootbankingsystem.model.accounttypes.*;
import com.example.springbootbankingsystem.repository.accountrepository.CheckingRepository;
import com.example.springbootbankingsystem.repository.accountrepository.CreditCardRepository;
import com.example.springbootbankingsystem.repository.accountrepository.SavingsRepository;
import com.example.springbootbankingsystem.repository.accountrepository.StudentCheckingRepository;
import com.example.springbootbankingsystem.repository.userrepository.AccountHolderRepository;
import com.example.springbootbankingsystem.service.interfaces.iaccount.IAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements IAccountService {

    private final AccountHolderRepository accountHolderRepository;

    private final SavingsRepository savingsRepository;
    private final SavingsDTOMapper savingsDTOMapper;

    private final CreditCardRepository creditCardRepository;
    private final CreditCardDTOMapper creditCardDTOMapper;

    private final CheckingRepository checkingRepository;
    private final CheckingDTOMapper checkingDTOMapper;

    private final StudentCheckingRepository studentCheckingRepository;
    private final StudentCheckingDTOMapper studentCheckingDTOMapper;

    LocalDate fechaActual = LocalDate.now();
    LocalDate fechaMinima = fechaActual.minusYears(24);

    //------------------ CHECKING ------------------------
    @Override
    public ResponseEntity<List<Checking>> getAllPrimaryOwnerChecking(Long idAccountHolder) {
        List<Checking> checkingList = accountHolderRepository.findById(idAccountHolder)
                .orElseThrow(() -> new IllegalStateException("No se ha encontrado la cuenta con el id " + idAccountHolder))
                .getPrimaryOwnerCheckingList()
                .stream()
                .sorted(Comparator.comparingLong(Checking::getId))
                .collect(Collectors.toList());
        return new ResponseEntity<>(checkingList, HttpStatus.FOUND);
    }
    @Override
    public ResponseEntity<List<Checking>> getAllSecondaryOwnerChecking(Long idAccountHolder) {
        List<Checking> checkingList = accountHolderRepository.findById(idAccountHolder)
                .orElseThrow(() -> new IllegalStateException("No se ha encontrado la cuenta con el id " + idAccountHolder))
                .getSecondaryOwnerCheckingList()
                .stream()
                .sorted(Comparator.comparingLong(Checking::getId))
                .collect(Collectors.toList());
        return new ResponseEntity<>(checkingList, HttpStatus.FOUND);
    }

    @Override
    public ResponseEntity<Checking> getPrimaryOwnerChecking(Long idChecking) {
        Checking checking = checkingRepository.findById(idChecking)
                .orElseThrow(() -> new IllegalStateException("No se ha encontrado el checking con el id " + idChecking));
        return new ResponseEntity<>(checking, HttpStatus.FOUND);
    }

    @Override
    public ResponseEntity<Checking> getSecondaryOwnerChecking(Long idChecking) {
        Checking checking = checkingRepository.findById(idChecking)
                .orElseThrow(() -> new IllegalStateException("No se ha encontrado el checking con el id " + idChecking));
        return new ResponseEntity<>(checking, HttpStatus.FOUND);
    }

    @Override
    public ResponseEntity<?> addNewChecking(CheckingDTO checkingDTO) {
        return null;
    }

    @Override
    public ResponseEntity<Checking> updateChecking(Checking checking) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteChecking(Long idAccountHolder, Long idChecking) {
        return null;
    }



    //------------------------------- SAVINGS ----------------------------------


    @Override
    public ResponseEntity<List<Savings>> getAllPrimaryOwnerSavings(Long idAccountHolder) {
        List<Savings> savingsList = accountHolderRepository.findById(idAccountHolder)
                .orElseThrow(() -> new IllegalStateException("No se ha encontrado la cuenta con el id " + idAccountHolder))
                .getPrimaryOwnerSavingsList()
                .stream()
                .sorted(Comparator.comparingLong(Savings::getId))
                .collect(Collectors.toList());
        return new ResponseEntity<>(savingsList, HttpStatus.FOUND);
    }

    @Override
    public ResponseEntity<List<Savings>> getAllSecondaryOwnerSavings(Long idAccountHolder) {
        List<Savings> savingsList = accountHolderRepository.findById(idAccountHolder)
                .orElseThrow(() -> new IllegalStateException("No se ha encontrado la cuenta con el id " + idAccountHolder))
                .getSecondaryOwnerSavingsList()
                .stream()
                .sorted(Comparator.comparingLong(Savings::getId))
                .collect(Collectors.toList());
        return new ResponseEntity<>(savingsList, HttpStatus.FOUND);
    }

    @Override
    public ResponseEntity<Savings> getSavings(Long idSavings) {
        Savings savings = savingsRepository.findById(idSavings)
                .orElseThrow(() -> new IllegalStateException("No se ha encontrado el checking con el id " + idSavings));
        return new ResponseEntity<>(savings, HttpStatus.FOUND);
    }

    @Override
    public ResponseEntity<Savings> addNewSavingAccount(SavingsDTO savingsDTO) {

        Savings savings = savingsDTOMapper.map(savingsDTO);

        if (accountHolderRepository.findById(savingsDTO.idAccountHolderPrimaryOwner()).isEmpty())
            throw new IllegalStateException("No se ha encontrado el id de la cuenta primaria");
        savings.setPrimaryOwner(accountHolderRepository.findById(savingsDTO.idAccountHolderPrimaryOwner()).get());

        if (savingsDTO.idAccountHolderSecondaryOwner() != null && accountHolderRepository.findById(savingsDTO.idAccountHolderSecondaryOwner()).isPresent())
            savings.setSecondaryOwner(accountHolderRepository.findById(savingsDTO.idAccountHolderSecondaryOwner()).get());
        else
            savings.setSecondaryOwner(null);

        return new ResponseEntity<>(savingsRepository.save(savings), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Savings> updateSavings(Long id, Savings savings) {
        Savings savings1 = savingsRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("No se ha encontrado el savings para actualizar"));

        if (savings.getBalance() != null &&
                !Objects.equals(savings1.getBalance(), savings.getBalance())){
            savings1.setBalance(savings.getBalance());
        }

        if (savings.getSecretKey() != null &&
                savings.getSecretKey().length() > 0 &&
                !Objects.equals(savings1.getSecretKey(), savings.getSecretKey())){
            savings1.setSecretKey(savings.getSecretKey());
        }

        if (savings.getStatus() != null &&
                !Objects.equals(savings1.getStatus(), savings.getStatus())){
            savings1.setStatus(savings.getStatus());
        }

        if (savings.getPenaltyFee() != null &&
                !Objects.equals(savings1.getPenaltyFee(), savings.getPenaltyFee())){
            savings1.setPenaltyFee(savings.getPenaltyFee());
        }

        if (savings.getCreatedDate() != null &&
                !Objects.equals(savings1.getCreatedDate(), savings.getCreatedDate())){
            savings1.setCreatedDate(savings.getCreatedDate());
        }

        if (savings.getUpdateDate() != null &&
                !Objects.equals(savings1.getUpdateDate(), savings.getUpdateDate())){
            savings1.setUpdateDate(savings.getUpdateDate());
        }

        if (savings1.isDeleted() != savings.isDeleted()){
            savings1.setStatus(savings.getStatus());
        }

        if (savings.getMinimumBalance() != null &&
                !Objects.equals(savings1.getMinimumBalance(), savings.getMinimumBalance())){
            savings1.setMinimumBalance(savings.getMinimumBalance());
        }

        if (savings.getInterestRate() != null &&
                !Objects.equals(savings1.getInterestRate(), savings.getInterestRate())){
            savings1.setInterestRate(savings.getInterestRate());
        }

        return new ResponseEntity<>(savingsRepository.save(savings1), HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<Void> deleteSavings(Long idChecking) {
        if (savingsRepository.findById(idChecking).isPresent())
            savingsRepository.deleteById(idChecking);
        return new ResponseEntity<>(HttpStatus.OK);
    }

/*
    @Override
    public ResponseEntity<Account> getPrimaryOwnerAccount(Long idAccountHolder, Long idAccount) {
        List<Account> accountList = accountHolderRepository.findById(idAccountHolder)
                .orElseThrow(() -> new IllegalStateException("No se ha encontrado la cuenta de usuario con el id " + idAccountHolder))
                .getPrimaryOwnerList();

        Account account = accountList.stream()
                .filter(account1 -> account1.getId().equals(idAccount))
                .findFirst().orElseThrow(() -> new IllegalStateException("No se ha encontrado la cuenta con el id " + idAccount));

        return new ResponseEntity<>(account, HttpStatus.FOUND);
    }

    @Override
    public ResponseEntity<Account> getSecondaryOwnerAccount(Long idAccountHolder, Long idAccount) {
        List<Account> accountList = accountHolderRepository.findById(idAccountHolder)
                .orElseThrow(() -> new IllegalStateException("No se ha encontrado la cuenta de usuario con el id " + idAccountHolder))
                .getSecondaryOwnerList();

        Account account = accountList.stream()
                .filter(account1 -> account1.getId().equals(idAccount))
                .findFirst().orElseThrow(() -> new IllegalStateException("No se ha encontrado la cuenta con el id " + idAccount));

        return new ResponseEntity<>(account, HttpStatus.FOUND);
    }

    //TODO terminar
    @Override
    public ResponseEntity<Void> deletePrimaryOwnerAccount(Long idAccountHolder, Long idAccount) {
        AccountHolder accountHolder = accountHolderRepository.findById(idAccountHolder)
                .orElseThrow(() -> new IllegalStateException("No se ha encontrado la cuenta de usuario con el id " + idAccountHolder));

        List<Account> accountList = accountHolder.getPrimaryOwnerList();

        Account account = accountList.stream()
                .filter(account1 -> account1.getId().equals(idAccount))
                .findFirst().orElseThrow(() -> new IllegalStateException("No se ha encontrado la cuenta con el id " + idAccount));

        accountList.remove(accountList.indexOf(account));
        accountHolder.setPrimaryOwnerList(accountList);
        accountHolderRepository.save(accountHolder);

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<Void> deleteSecondaryOwnerAccount(Long idAccountHolder, Long idAccount) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteAllPrimaryOwnerAccount(Long idAccountHolder) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteAllSecondaryOwnerAccount(Long idAccountHolder) {
        return null;
    }

    //------------------ SAVING ---------------------
    @Override
    public ResponseEntity<Savings> addNewSavingAccount(SavingsDTO savingsDTO) {

        Savings savings = savingsDTOMapper.map(savingsDTO);

        if (accountHolderRepository.findById(savingsDTO.idAccountHolderPrimaryOwner()).isEmpty())
            throw new IllegalStateException("No se ha encontrado el id de la cuenta primaria");
        savings.setPrimaryOwner(accountHolderRepository.findById(savingsDTO.idAccountHolderPrimaryOwner()).get());

        if (savingsDTO.idAccountHolderSecondaryOwner() != null && accountHolderRepository.findById(savingsDTO.idAccountHolderSecondaryOwner()).isPresent())
            savings.setSecondaryOwner(accountHolderRepository.findById(savingsDTO.idAccountHolderSecondaryOwner()).get());
        else
            savings.setSecondaryOwner(null);

        return new ResponseEntity<>(savingsAccountRepository.save(savings), HttpStatus.CREATED);
    }

    //--------------------- CREDIT-CARD ---------------------
    @Override
    public ResponseEntity<CreditCard> addNewCreditCard(CreditCardDTO creditCardDTO) {
        CreditCard creditCard = creditCardDTOMapper.map(creditCardDTO);

        if (accountHolderRepository.findById(creditCardDTO.idAccountHolderPrimaryOwner()).isEmpty())
            throw new IllegalStateException("No se ha encontrado el ID de la cuenta primaria");
        creditCard.setPrimaryOwner(accountHolderRepository.findById(creditCardDTO.idAccountHolderPrimaryOwner()).get());

        if (creditCardDTO.idAccountHolderSecondaryOwner() != null && accountHolderRepository.findById(creditCardDTO.idAccountHolderSecondaryOwner()).isPresent())
            creditCard.setSecondaryOwner(accountHolderRepository.findById(creditCardDTO.idAccountHolderSecondaryOwner()).get());
        else
            creditCard.setSecondaryOwner(null);

        return new ResponseEntity<>(creditCardRepository.save(creditCard), HttpStatus.CREATED);
    }

    //----------------------- CHECKING --------------------------
    @Override
    public ResponseEntity<?> addNewChecking(CheckingDTO checkingDTO) {
        Checking checking = checkingDTOMapper.map(checkingDTO);

        AccountHolder accountHolder = accountHolderRepository.findById(checkingDTO.idAccountHolderPrimaryOwner())
                .orElseThrow(() -> new IllegalStateException("No se ha encontrado la cuenta con ese ID"));

        LocalDate dateOfBirth = accountHolder.getDateOfBirth();

        if (!dateOfBirth.isAfter(fechaMinima)) {
            checking.setPrimaryOwner(accountHolder);
        }
        else {
            StudentCheckingDTO studentCheckingDTO = StudentCheckingDTO.builder()
                    .balance(checkingDTO.balance())
                    .secretKey(checkingDTO.secretKey())
                    .idAccountHolderPrimaryOwner(checkingDTO.idAccountHolderPrimaryOwner())
                    .idAccountHolderSecondaryOwner(checkingDTO.idAccountHolderSecondaryOwner())
                    .build();

            return addNewStudentChecking(studentCheckingDTO);
        }

        if (checkingDTO.idAccountHolderSecondaryOwner() != null && accountHolderRepository.findById(checkingDTO.idAccountHolderSecondaryOwner()).isPresent())
            checking.setSecondaryOwner(accountHolderRepository.findById(checkingDTO.idAccountHolderSecondaryOwner()).get());
        else
            checking.setSecondaryOwner(null);

        return new ResponseEntity<>(checkingRepository.save(checking), HttpStatus.CREATED);
    }

    //------------------------ STUDENT-CHECKING -------------------------
    @Override
    public ResponseEntity<?> addNewStudentChecking(StudentCheckingDTO studentCheckingDTO) {
        StudentChecking studentChecking = studentCheckingDTOMapper.map(studentCheckingDTO);

        AccountHolder accountHolder = accountHolderRepository.findById(studentCheckingDTO.idAccountHolderPrimaryOwner())
                .orElseThrow(() -> new IllegalStateException("No se ha encontrado la cuenta con ese ID"));

        LocalDate dateOfBirth = accountHolder.getDateOfBirth();

        if (dateOfBirth.isAfter(fechaMinima))
            studentChecking.setPrimaryOwner(accountHolder);
        else
            throw new IllegalStateException("La edad máxima es de 23 años");

        if (studentCheckingDTO.idAccountHolderSecondaryOwner() != null && accountHolderRepository.findById(studentCheckingDTO.idAccountHolderSecondaryOwner()).isPresent())
            studentChecking.setSecondaryOwner(accountHolderRepository.findById(studentCheckingDTO.idAccountHolderSecondaryOwner()).get());
        else
            studentChecking.setSecondaryOwner(null);

        return new ResponseEntity<>(studentCheckingRepository.save(studentChecking), HttpStatus.CREATED);
    }


    //--------------------- OTHER -----------------------------
    public void chargeMonthlyFee() {
        LocalDate now = LocalDate.now();
        List<Checking> checkingAccounts = checkingRepository.findAll();

        for (Checking checking :checkingAccounts) {                     //antes
            if (checking.getLastMaintenanceFee().plusMonths(1).isBefore(now)) {
                checking.setLastMaintenanceFee(now);
                checking.setBalance(checking.getBalance().subtract(checking.getMonthlyMaintenanceFee()));
            }

            //Si el saldo de la cuenta es menor a 0, se congelará
            if (checking.getBalance().compareTo(BigDecimal.ZERO) < 0) {
                checking.setStatus(Status.FROZEN);
            }

            //Si el saldo de la cuenta es menor al mínimo saldo, se cobrara una multa
            if (checking.getBalance().compareTo(checking.getMinimumBalance()) < 0) {
                checking.setBalance(checking.getBalance().subtract(checking.getPenaltyFee()));
            }

            checking.setUpdateDate(LocalDate.now());

            checkingRepository.save(checking);
        }
    }*/
}
