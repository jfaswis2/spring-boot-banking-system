package com.example.springbootbankingsystem.dto.accountdto;

import java.math.BigDecimal;

public record CheckingDTO(
        Long idAccountHolderPrimaryOwner,
        Long idAccountHolderSecondaryOwner,
        String secretKey,
        BigDecimal balance
) {
}
