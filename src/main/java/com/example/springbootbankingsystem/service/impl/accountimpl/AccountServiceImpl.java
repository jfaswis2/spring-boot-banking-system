package com.example.springbootbankingsystem.service.impl.accountimpl;

import com.example.springbootbankingsystem.dto.accountdto.SavingsDTO;
import com.example.springbootbankingsystem.mapper.accountmapper.SavingsDTOMapper;
import com.example.springbootbankingsystem.model.accounttypes.Savings;
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

    private final SavingsAccountRepository savingsAccountRepository;
    private final SavingsDTOMapper savingsDTOMapper;

    private final AccountHolderRepository accountHolderRepository;

    @Override
    public ResponseEntity<Savings> addNewSavingAccount(SavingsDTO savingsDTO) {

        Savings savings = savingsDTOMapper.map(savingsDTO);

        if (accountHolderRepository.findById(savingsDTO.idAccountHolderPrimaryOwner()).isEmpty())
            throw new IllegalStateException("No se ha encontrado el id");
        savings.setPrimaryOwner(accountHolderRepository.findById(savingsDTO.idAccountHolderPrimaryOwner()).get());

        if (savingsDTO.idAccountHolderSecondaryOwner() != null && accountHolderRepository.findById(savingsDTO.idAccountHolderSecondaryOwner()).isPresent())
            savings.setSecondaryOwner(accountHolderRepository.findById(savingsDTO.idAccountHolderSecondaryOwner()).get());
        else
            savings.setSecondaryOwner(null);

        return new ResponseEntity<>(savingsAccountRepository.save(savings), HttpStatus.CREATED);
    }
}
