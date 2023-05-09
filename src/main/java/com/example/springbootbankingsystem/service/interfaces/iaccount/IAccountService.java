package com.example.springbootbankingsystem.service.interfaces.iaccount;

import com.example.springbootbankingsystem.dto.accountdto.SavingsDTO;
import com.example.springbootbankingsystem.model.accounttypes.Savings;
import org.springframework.http.ResponseEntity;

public interface IAccountService {

    ResponseEntity<Savings> addNewSavingAccount(SavingsDTO savingsDTO);
}
