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
@RequiredArgsConstructor
@Table(name = "t_savings_account")
public class Savings extends Account {
    private String secretKey;
    private Status status;
    private BigDecimal minimumBalance = BigDecimal.valueOf(1000L);
    @DecimalMin(value = "100", message = "Las cuentas de ahorro deben de crearse por encima de los 100")
    private BigDecimal balance;
    @DecimalMax(value = "0.5", message = "La tasa de interés no puede ser mayor a 0.5")
    @Min(value = 0, message = "La tasa de interés no puede ser menor a 0")
    private BigDecimal interestRate = BigDecimal.valueOf(0.0025);
}
