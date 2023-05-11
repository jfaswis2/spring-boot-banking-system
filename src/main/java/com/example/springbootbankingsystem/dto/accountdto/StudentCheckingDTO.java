package com.example.springbootbankingsystem.dto.accountdto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record StudentCheckingDTO(
        Long idAccountHolderPrimaryOwner,
        Long idAccountHolderSecondaryOwner,
        String secretKey,
        BigDecimal balance
) {
}
