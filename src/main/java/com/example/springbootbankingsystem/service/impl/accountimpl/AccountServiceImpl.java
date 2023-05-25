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
import com.example.springbootbankingsystem.repository.accountrepository.SavingsRepository;
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
    public ResponseEntity<Checking> getCheckingAccount(Long idChecking) {
        Checking checking = checkingRepository.findById(idChecking)
                .orElseThrow(() -> new IllegalStateException("No se ha encontrado el checking con el id " + idChecking));
        return new ResponseEntity<>(checking, HttpStatus.FOUND);
    }

    @Override
    public ResponseEntity<?> addNewCheckingAccount(CheckingDTO checkingDTO) {
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

            return addNewStudentCheckingAccount(studentCheckingDTO);
        }

        if (checkingDTO.idAccountHolderSecondaryOwner() != null && accountHolderRepository.findById(checkingDTO.idAccountHolderSecondaryOwner()).isPresent())
            checking.setSecondaryOwner(accountHolderRepository.findById(checkingDTO.idAccountHolderSecondaryOwner()).get());
        else
            checking.setSecondaryOwner(null);

        return new ResponseEntity<>(checkingRepository.save(checking), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Checking> updateCheckingAccount(Long id, Checking checking) {
        Checking checking1 = checkingRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("No se ha encontrado el Checking para actualizar"));

        if (checking.getSecretKey() != null &&
                checking.getSecretKey().length() > 0 &&
                !Objects.equals(checking1.getSecretKey(), checking.getSecretKey())) {
            checking1.setSecretKey(checking.getSecretKey());
        }

        if (checking.getBalance() != null &&
                !Objects.equals(checking1.getBalance(), checking.getBalance())) {
            checking1.setBalance(checking.getBalance());
        }

        if (checking.getMinimumBalance() != null &&
                !Objects.equals(checking1.getMinimumBalance(), checking.getMinimumBalance())) {
            checking1.setMinimumBalance(checking.getMinimumBalance());
        }

        if (checking.getMonthlyMaintenanceFee() != null &&
                !Objects.equals(checking1.getMonthlyMaintenanceFee(), checking.getMonthlyMaintenanceFee())) {
            checking1.setMonthlyMaintenanceFee(checking.getMonthlyMaintenanceFee());
        }

        if (checking.getLastMaintenanceFee() != null &&
                !Objects.equals(checking1.getLastMaintenanceFee(), checking.getLastMaintenanceFee())) {
            checking1.setLastMaintenanceFee(checking.getLastMaintenanceFee());
        }

        if (checking.getPenaltyFee() != null &&
                !Objects.equals(checking1.getPenaltyFee(), checking.getPenaltyFee())) {
            checking1.setPenaltyFee(checking.getPenaltyFee());
        }

        if (checking.getCreatedDate() != null &&
                !Objects.equals(checking1.getCreatedDate(), checking.getCreatedDate())) {
            checking1.setCreatedDate(checking.getCreatedDate());
        }

        if (checking.getUpdateDate() != null &&
                !Objects.equals(checking1.getUpdateDate(), checking.getUpdateDate())) {
            checking1.setUpdateDate(checking.getUpdateDate());
        }

        if (checking1.isDeleted() != checking.isDeleted()) {
            checking1.setDeleted(checking.isDeleted());
        }

        if (checking.getStatus() != null &&
                !Objects.equals(checking1.getStatus(), checking.getStatus())) {
            checking1.setStatus(checking.getStatus());
        }

        return new ResponseEntity<>(checkingRepository.save(checking1), HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<Void> deleteCheckingAccount(Long idChecking) {
        return null;
    }

    //----------------------- STUDENT-CHECKING --------------------------
    @Override
    public ResponseEntity<List<StudentChecking>> getAllPrimaryOwnerStudentChecking(Long idAccountHolder) {
        List<StudentChecking> studentCheckingList = accountHolderRepository.findById(idAccountHolder)
                .orElseThrow(() -> new IllegalStateException("No se ha encontrado la cuenta con el id " + idAccountHolder))
                .getPrimaryOwnerStudentCheckingList()
                .stream()
                .sorted(Comparator.comparingLong(StudentChecking::getId))
                .collect(Collectors.toList());
        return new ResponseEntity<>(studentCheckingList, HttpStatus.FOUND);
    }

    @Override
    public ResponseEntity<List<StudentChecking>> getAllSecondaryOwnerStudentChecking(Long idAccountHolder) {
        List<StudentChecking> studentCheckingList = accountHolderRepository.findById(idAccountHolder)
                .orElseThrow(() -> new IllegalStateException("No se ha encontrado la cuenta con el id " + idAccountHolder))
                .getSecondaryOwnerStudentCheckingList()
                .stream()
                .sorted(Comparator.comparingLong(StudentChecking::getId))
                .collect(Collectors.toList());
        return new ResponseEntity<>(studentCheckingList, HttpStatus.FOUND);
    }

    @Override
    public ResponseEntity<StudentChecking> getStudentCheckingAccount(Long idStudentChecking) {
        StudentChecking studentChecking = studentCheckingRepository.findById(idStudentChecking)
                .orElseThrow(() -> new IllegalStateException("No se ha encontrado el Student-Checking con el id " + idStudentChecking));
        return new ResponseEntity<>(studentChecking, HttpStatus.FOUND);
    }

    @Override
    public ResponseEntity<?> addNewStudentCheckingAccount(StudentCheckingDTO studentCheckingDTO) {
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

    @Override
    public ResponseEntity<StudentChecking> updateStudentCheckingAccount(Long id, StudentChecking studentChecking) {
        StudentChecking studentChecking1 = studentCheckingRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("No se ha encontrado el savings para actualizar"));

        if (studentChecking.getSecretKey() != null &&
                studentChecking.getSecretKey().length() > 0 &&
                !Objects.equals(studentChecking1.getSecretKey(), studentChecking.getSecretKey())){
            studentChecking1.setSecretKey(studentChecking.getSecretKey());
        }

        if (studentChecking.getBalance() != null &&
                !Objects.equals(studentChecking1.getBalance(), studentChecking.getBalance())){
            studentChecking1.setBalance(studentChecking.getBalance());
        }

        if (studentChecking.getStatus() != null &&
                !Objects.equals(studentChecking1.getStatus(), studentChecking.getStatus())){
            studentChecking1.setStatus(studentChecking.getStatus());
        }

        if (studentChecking.getPenaltyFee() != null &&
                !Objects.equals(studentChecking1.getPenaltyFee(), studentChecking.getPenaltyFee())){
            studentChecking1.setPenaltyFee(studentChecking.getPenaltyFee());
        }

        if (studentChecking.getCreatedDate() != null &&
                !Objects.equals(studentChecking1.getCreatedDate(), studentChecking.getCreatedDate())){
            studentChecking1.setCreatedDate(studentChecking.getCreatedDate());
        }

        if (studentChecking.getUpdateDate() != null &&
                !Objects.equals(studentChecking1.getUpdateDate(), studentChecking.getUpdateDate())){
            studentChecking1.setUpdateDate(studentChecking.getUpdateDate());
        }

        if (studentChecking1.isDeleted() != studentChecking.isDeleted()){
            studentChecking1.setStatus(studentChecking.getStatus());
        }

        return new ResponseEntity<>(studentCheckingRepository.save(studentChecking1), HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<Void> deleteStudentCheckingAccount(Long idStudentChecking) {
        if (studentCheckingRepository.findById(idStudentChecking).isEmpty())
            throw new IllegalStateException("No se ha encontrado el Credit-Card con el ID: " + idStudentChecking);
        studentCheckingRepository.deleteById(idStudentChecking);
        return new ResponseEntity<>(HttpStatus.OK);
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
    public ResponseEntity<Savings> getSavingsAccount(Long idSavings) {
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

        if (savingsDTO.idAccountHolderSecondaryOwner() != null && accountHolderRepository
                .findById(savingsDTO.idAccountHolderSecondaryOwner()).isPresent())
            savings.setSecondaryOwner(accountHolderRepository.findById(savingsDTO.idAccountHolderSecondaryOwner()).get());
        else
            savings.setSecondaryOwner(null);

        return new ResponseEntity<>(savingsRepository.save(savings), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Savings> updateSavingsAccount(Long id, Savings savings) {
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
    public ResponseEntity<Void> deleteSavingsAccount(Long idChecking) {
        if (savingsRepository.findById(idChecking).isEmpty())
            throw new IllegalStateException("No se ha encontrado el Credit-Card con el ID: " + idChecking);
        savingsRepository.deleteById(idChecking);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //-------------------- CREDIT-CARD ---------------------------
    @Override
    public ResponseEntity<List<CreditCard>> getAllPrimaryOwnerCreditCard(Long idAccountHolder) {
        List<CreditCard> creditCardList = accountHolderRepository.findById(idAccountHolder)
                .orElseThrow(() -> new IllegalStateException("No se ha encontrado la cuenta con el id " + idAccountHolder))
                .getPrimaryOwnerCreditCardList()
                .stream()
                .sorted(Comparator.comparingLong(CreditCard::getId))
                .collect(Collectors.toList());
        return new ResponseEntity<>(creditCardList, HttpStatus.FOUND);
    }

    @Override
    public ResponseEntity<List<CreditCard>> getAllSecondaryOwnerCreditCard(Long idAccountHolder) {
        List<CreditCard> creditCardList = accountHolderRepository.findById(idAccountHolder)
                .orElseThrow(() -> new IllegalStateException("No se ha encontrado la cuenta con el id " + idAccountHolder))
                .getSecondaryOwnerCreditCardList()
                .stream()
                .sorted(Comparator.comparingLong(CreditCard::getId))
                .collect(Collectors.toList());
        return new ResponseEntity<>(creditCardList, HttpStatus.FOUND);
    }

    @Override
    public ResponseEntity<CreditCard> getCreditCardAccount(Long idCreditCard) {
        CreditCard creditCard = creditCardRepository.findById(idCreditCard)
                .orElseThrow(() -> new IllegalStateException("No se ha encontrado el checking con el id " + idCreditCard));
        return new ResponseEntity<>(creditCard, HttpStatus.FOUND);
    }

    @Override
    public ResponseEntity<CreditCard> addNewCreditCardAccount(CreditCardDTO creditCardDTO) {



        CreditCard creditCard = creditCardDTOMapper.map(creditCardDTO);

        if (accountHolderRepository.findById(creditCardDTO.idAccountHolderPrimaryOwner()).isEmpty())
            throw new IllegalStateException("No se ha encontrado el id de la cuenta primaria");
        creditCard.setPrimaryOwner(accountHolderRepository.findById(creditCardDTO.idAccountHolderPrimaryOwner()).get());

        if (creditCardDTO.idAccountHolderSecondaryOwner() != null && accountHolderRepository
                .findById(creditCardDTO.idAccountHolderSecondaryOwner()).isPresent())
            creditCard.setSecondaryOwner(accountHolderRepository.findById(creditCardDTO.idAccountHolderSecondaryOwner()).get());
        else
            creditCard.setSecondaryOwner(null);

        return new ResponseEntity<>(creditCardRepository.save(creditCard), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<CreditCard> updateCreditCardAccount(Long id, CreditCard creditCard) {
        CreditCard creditCard1 = creditCardRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("No se ha encontrado el credit-card para actualizar"));

        if (creditCard.getBalance() != null &&
                !Objects.equals(creditCard1.getBalance(), creditCard.getBalance())) {
            creditCard1.setBalance(creditCard.getBalance());
        }

        if (creditCard.getPenaltyFee() != null &&
                !Objects.equals(creditCard1.getPenaltyFee(), creditCard.getPenaltyFee())) {
            creditCard1.setPenaltyFee(creditCard.getPenaltyFee());
        }

        if (creditCard.getCreatedDate() != null &&
                !Objects.equals(creditCard1.getCreatedDate(), creditCard.getCreatedDate())) {
            creditCard1.setCreatedDate(creditCard.getCreatedDate());
        }

        if (creditCard.getUpdateDate() != null &&
                !Objects.equals(creditCard1.getUpdateDate(), creditCard.getUpdateDate())) {
            creditCard1.setUpdateDate(creditCard.getUpdateDate());
        }

        if (creditCard1.isDeleted() != creditCard.isDeleted()) {
            creditCard1.setDeleted(creditCard.isDeleted());
        }

        if (creditCard.getCreditLimit() != null &&
                !Objects.equals(creditCard1.getCreditLimit(), creditCard.getCreditLimit())) {
            creditCard1.setCreditLimit(creditCard.getCreditLimit());
        }

        if (creditCard.getInterestRate() != null &&
                !Objects.equals(creditCard1.getInterestRate(), creditCard.getInterestRate())) {
            creditCard1.setInterestRate(creditCard.getInterestRate());
        }

        return new ResponseEntity<>(creditCardRepository.save(creditCard1), HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<Void> deleteCreditCardAccount(Long idCreditCard) {
        if (creditCardRepository.findById(idCreditCard).isEmpty())
            throw new IllegalStateException("No se ha encontrado el Credit-Card con el ID: " + idCreditCard);

        creditCardRepository.deleteById(idCreditCard);
        return new ResponseEntity<>(HttpStatus.OK);
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
