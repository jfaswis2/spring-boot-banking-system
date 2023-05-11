package com.example.springbootbankingsystem.mapper.accountmapper;

import com.example.springbootbankingsystem.dto.accountdto.CreditCardDTO;
import com.example.springbootbankingsystem.mapper.IMapper;
import com.example.springbootbankingsystem.model.accounttypes.CreditCard;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class CreditCardDTOMapper implements IMapper<CreditCardDTO, CreditCard> {
    @Override
    public CreditCard map(CreditCardDTO in) {
        CreditCard creditCard = new CreditCard();
        creditCard.setBalance(in.balance());
        creditCard.setInterestRate(in.interestRate());
        creditCard.setCreditLimit(in.creditLimit());
        creditCard.setPenaltyFee(BigDecimal.valueOf(0));
        creditCard.setCreatedDate(LocalDate.now());
        creditCard.setUpdateDate(LocalDate.now());
        creditCard.setDeleted(false);

        return creditCard;
    }
}
