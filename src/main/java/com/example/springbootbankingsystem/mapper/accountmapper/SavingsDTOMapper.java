package com.example.springbootbankingsystem.mapper.accountmapper;

import com.example.springbootbankingsystem.dto.accountdto.SavingsDTO;
import com.example.springbootbankingsystem.mapper.IMapper;
import com.example.springbootbankingsystem.model.accounttypes.Savings;
import com.example.springbootbankingsystem.repository.userrepository.AccountHolderRepository;
import com.example.springbootbankingsystem.utils.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class SavingsDTOMapper implements IMapper<SavingsDTO, Savings> {

    @Override
    public Savings map(SavingsDTO in) {
        Savings savings = new Savings();
        savings.setBalance(in.balance());
        savings.setSecretKey(in.secretKey());
        savings.setInterestRate(in.interestRate());
        savings.setPenaltyFee(BigDecimal.valueOf(40L));
        savings.setMinimumBalance(BigDecimal.valueOf(1000));
        savings.setStatus(Status.ACTIVE);
        savings.setCreatedDate(LocalDate.now());
        savings.setUpdateDate(LocalDate.now());
        savings.setDeleted(false);

        return savings;
    }
}
