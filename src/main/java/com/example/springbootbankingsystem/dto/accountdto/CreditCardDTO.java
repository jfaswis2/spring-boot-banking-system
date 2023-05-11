package com.example.springbootbankingsystem.dto.accountdto;

import java.math.BigDecimal;

public record CreditCardDTO(
        Long idAccountHolderPrimaryOwner,
        Long idAccountHolderSecondaryOwner,
        BigDecimal balance,
        BigDecimal interestRate,
        BigDecimal creditLimit
) {
}
