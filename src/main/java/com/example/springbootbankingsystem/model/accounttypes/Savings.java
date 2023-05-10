package com.example.springbootbankingsystem.model.accounttypes;

import com.example.springbootbankingsystem.utils.Status;
import jakarta.persistence.*;
import jakarta.validation.OverridesAttribute;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;


import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
//@RequiredArgsConstructor
@Table(name = "t_savings_account")
public class Savings extends Account {
    private String secretKey;

    @DecimalMin(value = "100", message = "Las cuentas de ahorro deben de crearse por encima de los 100")
    private BigDecimal balance;

    private BigDecimal minimumBalance = BigDecimal.valueOf(1000L);

    @DecimalMax(value = "0.5", message = "La tasa de interés no puede ser mayor a 0.5")
    @Min(value = 0, message = "La tasa de interés no puede ser menor a 0")
    private BigDecimal interestRate = BigDecimal.valueOf(0.0025);

    private Status status;

    public Savings(String secretKey, BigDecimal balance, BigDecimal minimumBalance, BigDecimal interestRate, Status status) {
        super();
        this.secretKey = secretKey;
        this.balance = balance;
        this.minimumBalance = minimumBalance;
        this.interestRate = interestRate;
        this.status = status;
    }
}
