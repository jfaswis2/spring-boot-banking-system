package com.example.springbootbankingsystem.model.accounttypes;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "t_creditcard_account")
public class CreditCard extends Account {
    private BigDecimal balance;

    @DecimalMax(value = "100000", message = "El límite de crédito no puede ser superior a 100000")
    @DecimalMin(value = "100", message = "El límite de crédito no puede ser inferior a 100")
    private BigDecimal creditLimit = BigDecimal.valueOf(100L);

    @DecimalMax(value = "0.2", message = "La tasa de interés no puede ser mayor a 0.2")
    @DecimalMin(value = "0.1", message = "La tasa de interés no puede ser menor a 0.1")
    private BigDecimal interestRate = BigDecimal.valueOf(0.2);
}
