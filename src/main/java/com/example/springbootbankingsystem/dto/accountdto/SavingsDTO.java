package com.example.springbootbankingsystem.dto.accountdto;

import jakarta.annotation.Nullable;

import java.math.BigDecimal;

public record SavingsDTO(
        Long idAccountHolderPrimaryOwner,
        Long idAccountHolderSecondaryOwner,
        BigDecimal balance,
        String secretKey,
        BigDecimal interestRate
) {
}
