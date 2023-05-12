package com.example.springbootbankingsystem.mapper.accountmapper;

import com.example.springbootbankingsystem.dto.accountdto.CheckingDTO;
import com.example.springbootbankingsystem.mapper.IMapper;
import com.example.springbootbankingsystem.model.accounttypes.Checking;
import com.example.springbootbankingsystem.utils.Status;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class CheckingDTOMapper implements IMapper<CheckingDTO, Checking> {

    @Override
    public Checking map(CheckingDTO in) {
        Checking checking = new Checking();
        checking.setSecretKey(in.secretKey());
        checking.setBalance(in.balance());
        checking.setPenaltyFee(BigDecimal.valueOf(40L));
        checking.setMinimumBalance(BigDecimal.valueOf(250L));
        checking.setMonthlyMaintenanceFee(BigDecimal.valueOf(12L));
        checking.setStatus(Status.ACTIVE);
        checking.setCreatedDate(LocalDate.now());
        checking.setUpdateDate(LocalDate.now());
        checking.setLastMaintenanceFee(LocalDate.now());
        checking.setDeleted(false);

        return checking;
    }
}
