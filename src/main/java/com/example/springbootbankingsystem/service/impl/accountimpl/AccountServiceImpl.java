package com.example.springbootbankingsystem.service.impl.accountimpl;

import com.example.springbootbankingsystem.dto.accountdto.CreditCardDTO;
import com.example.springbootbankingsystem.dto.accountdto.SavingsDTO;
import com.example.springbootbankingsystem.mapper.accountmapper.CreditCardDTOMapper;
import com.example.springbootbankingsystem.mapper.accountmapper.SavingsDTOMapper;
import com.example.springbootbankingsystem.model.accounttypes.CreditCard;
import com.example.springbootbankingsystem.model.accounttypes.Savings;
import com.example.springbootbankingsystem.repository.accountrepository.CreditCardRepository;
import com.example.springbootbankingsystem.repository.accountrepository.SavingsAccountRepository;
import com.example.springbootbankingsystem.repository.userrepository.AccountHolderRepository;
import com.example.springbootbankingsystem.service.interfaces.iaccount.IAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements IAccountService {

    private final AccountHolderRepository accountHolderRepository;

    private final SavingsAccountRepository savingsAccountRepository;
    private final SavingsDTOMapper savingsDTOMapper;

    private final CreditCardRepository creditCardRepository;
    private final CreditCardDTOMapper creditCardDTOMapper;



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

    @Override
    public ResponseEntity<CreditCard> addNewCreditCard(CreditCardDTO creditCardDTO) {
        CreditCard creditCard = creditCardDTOMapper.map(creditCardDTO);

        if (accountHolderRepository.findById(creditCardDTO.idAccountHolderPrimaryOwner()).isEmpty())
            throw new IllegalStateException("No se ha encontrado el id de la cuenta primaria");
        creditCard.setPrimaryOwner(accountHolderRepository.findById(creditCardDTO.idAccountHolderPrimaryOwner()).get());

        if (creditCardDTO.idAccountHolderSecondaryOwner() != null && accountHolderRepository.findById(creditCardDTO.idAccountHolderSecondaryOwner()).isPresent())
            creditCard.setSecondaryOwner(accountHolderRepository.findById(creditCardDTO.idAccountHolderSecondaryOwner()).get());
        else
            creditCard.setSecondaryOwner(null);

        return new ResponseEntity<>(creditCardRepository.save(creditCard), HttpStatus.CREATED);
    }
}
