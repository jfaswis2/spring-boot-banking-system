package com.example.springbootbankingsystem.service.impl.accountimpl;

import com.example.springbootbankingsystem.dto.accountdto.CheckingDTO;
import com.example.springbootbankingsystem.dto.accountdto.CreditCardDTO;
import com.example.springbootbankingsystem.dto.accountdto.SavingsDTO;
import com.example.springbootbankingsystem.dto.accountdto.StudentCheckingDTO;
import com.example.springbootbankingsystem.mapper.accountmapper.CheckingDTOMapper;
import com.example.springbootbankingsystem.mapper.accountmapper.CreditCardDTOMapper;
import com.example.springbootbankingsystem.mapper.accountmapper.SavingsDTOMapper;
import com.example.springbootbankingsystem.mapper.accountmapper.StudentCheckingDTOMapper;
import com.example.springbootbankingsystem.model.accounttypes.*;
import com.example.springbootbankingsystem.model.usertypes.AccountHolder;
import com.example.springbootbankingsystem.repository.accountrepository.CheckingRepository;
import com.example.springbootbankingsystem.repository.accountrepository.CreditCardRepository;
import com.example.springbootbankingsystem.repository.accountrepository.SavingsAccountRepository;
import com.example.springbootbankingsystem.repository.accountrepository.StudentCheckingRepository;
import com.example.springbootbankingsystem.repository.userrepository.AccountHolderRepository;
import com.example.springbootbankingsystem.service.interfaces.iaccount.IAccountService;
import com.example.springbootbankingsystem.utils.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements IAccountService {

    private final AccountHolderRepository accountHolderRepository;

    private final SavingsAccountRepository savingsAccountRepository;
    private final SavingsDTOMapper savingsDTOMapper;

    private final CreditCardRepository creditCardRepository;
    private final CreditCardDTOMapper creditCardDTOMapper;

    private final CheckingRepository checkingRepository;
    private final CheckingDTOMapper checkingDTOMapper;

    private final StudentCheckingRepository studentCheckingRepository;
    private final StudentCheckingDTOMapper studentCheckingDTOMapper;

    LocalDate fechaActual = LocalDate.now();
    LocalDate fechaMinima = fechaActual.minusYears(24);

    //------------------ ACCOUNT ------------------------
    @Override
    public ResponseEntity<List<Account>> getAllPrimaryOwnerAccount(Long idAccountHolder) {
        List<Account> accountList = accountHolderRepository.findById(idAccountHolder)
                .orElseThrow(() -> new IllegalStateException("No se ha encontrado la cuenta con el id " + idAccountHolder))
                .getPrimaryOwnerList()
                .stream()
                .sorted(Comparator.comparingLong(Account::getId))
                .collect(Collectors.toList());
        return new ResponseEntity<>(accountList, HttpStatus.FOUND);
    }

    @Override
    public ResponseEntity<List<Account>> getAllSecondaryOwnerAccount(Long idAccountHolder) {
        List<Account> accountList = accountHolderRepository.findById(idAccountHolder)
                .orElseThrow(() -> new IllegalStateException("No se ha encontrado la cuenta con el id " + idAccountHolder))
                .getSecondaryOwnerList()
                .stream()
                .sorted(Comparator.comparingLong(Account::getId))
                .collect(Collectors.toList());
        return new ResponseEntity<>(accountList, HttpStatus.FOUND);
    }

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

    @Override
    public ResponseEntity<Void> deletePrimaryOwnerAccount(Long idAccountHolder) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteSecondaryOwnerAccount(Long idAccountHolder) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteAllAccount(Long idAccountHolder) {
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
    }
}
