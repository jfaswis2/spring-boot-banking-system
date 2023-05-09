package com.example.springbootbankingsystem.service.impl;

import com.example.springbootbankingsystem.dto.AccountHolderDTO;
import com.example.springbootbankingsystem.mapper.AccountHolderDTOMapper;
import com.example.springbootbankingsystem.model.usertypes.AccountHolder;
import com.example.springbootbankingsystem.repository.AccountHolderRepository;
import com.example.springbootbankingsystem.service.interfaces.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final AccountHolderRepository accountHolderRepository;

    private final AccountHolderDTOMapper accountHolderDTOMapper;

    @Override
    public ResponseEntity<AccountHolder> addNewAccountHolder(AccountHolderDTO accountHolderDTO) {
        Optional<AccountHolder> accountHolderOptional = accountHolderRepository
                .findAccountHolderByEmail(accountHolderDTO.email());
        if (accountHolderOptional.isPresent()) {
            throw new IllegalStateException("Ya hay un email vinculado a esta cuenta");
        }

        return new ResponseEntity<>(accountHolderRepository.save(accountHolderDTOMapper.map(accountHolderDTO)), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<AccountHolder> getAccountHolder(Long id) {
        Optional<AccountHolder> accountHolderOptional = accountHolderRepository
                .findById(id);
        if (accountHolderOptional.isEmpty()) {
            throw new IllegalStateException("No existe una cuenta vinculada a este ID");
        }

        return new ResponseEntity<>(accountHolderOptional.get(), HttpStatus.OK);
    }
}
