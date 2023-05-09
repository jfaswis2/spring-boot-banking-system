package com.example.springbootbankingsystem.mapper;

import com.example.springbootbankingsystem.dto.AccountHolderDTO;
import com.example.springbootbankingsystem.model.usertypes.AccountHolder;
import com.example.springbootbankingsystem.utils.Role;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Component
public class AccountHolderDTOMapper implements IMapper<AccountHolderDTO, AccountHolder>{
    @Override
    public AccountHolder map(AccountHolderDTO in) {
        AccountHolder accountHolder = new AccountHolder();
        accountHolder.setName(in.name());
        accountHolder.setEmail(in.email());
        accountHolder.setPassword(in.password());
        accountHolder.setRole(Role.ACCOUNT_HOLDER);
        accountHolder.setCreatedDate(LocalDate.now());
        accountHolder.setUpdateDate(LocalDate.now());
        accountHolder.setDeleted(false);
        accountHolder.setDateOfBirth(in.dateOfBirth());

        return accountHolder;
    }
}
