package com.example.springbootbankingsystem.service.impl;

import com.example.springbootbankingsystem.dto.AccountHolderDTO;
import com.example.springbootbankingsystem.mapper.AccountHolderDTOMapper;
import com.example.springbootbankingsystem.model.usertypes.AccountHolder;
import com.example.springbootbankingsystem.repository.AccountHolderRepository;
import com.example.springbootbankingsystem.service.interfaces.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
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

        return ResponseEntity.ok(accountHolderRepository.save(accountHolderDTOMapper.map(accountHolderDTO)));
    }
}
