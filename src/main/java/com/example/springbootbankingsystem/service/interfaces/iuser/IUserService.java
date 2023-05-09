package com.example.springbootbankingsystem.service.interfaces.iuser;

import com.example.springbootbankingsystem.dto.userdto.AccountHolderDTO;
import com.example.springbootbankingsystem.model.usertypes.AccountHolder;
import org.springframework.http.ResponseEntity;

public interface IUserService {

    ResponseEntity<AccountHolder> addNewAccountHolder(AccountHolderDTO accountHolderDTO);

    ResponseEntity<AccountHolder> getAccountHolder(Long id);
}
