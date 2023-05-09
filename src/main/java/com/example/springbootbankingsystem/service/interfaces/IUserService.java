package com.example.springbootbankingsystem.service.interfaces;

import com.example.springbootbankingsystem.dto.AccountHolderDTO;
import com.example.springbootbankingsystem.model.usertypes.AccountHolder;
import org.springframework.http.ResponseEntity;

public interface IUserService {

    ResponseEntity<AccountHolder> addNewAccountHolder(AccountHolderDTO accountHolderDTO);
}
