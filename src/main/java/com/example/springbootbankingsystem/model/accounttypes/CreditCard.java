package com.example.springbootbankingsystem.model.accounttypes;

import com.example.springbootbankingsystem.model.usertypes.AccountHolder;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "t_creditcard_account")
public class CreditCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal balance;
    private BigDecimal penaltyFee;
    private LocalDate createdDate;
    private LocalDate updateDate;
    private boolean isDeleted;

    @DecimalMax(value = "100000", message = "El límite de crédito no puede ser superior a 100000")
    @DecimalMin(value = "100", message = "El límite de crédito no puede ser inferior a 100")
    private BigDecimal creditLimit = BigDecimal.valueOf(100L);

    @DecimalMax(value = "0.2", message = "La tasa de interés no puede ser mayor a 0.2")
    @DecimalMin(value = "0.1", message = "La tasa de interés no puede ser menor a 0.1")
    private BigDecimal interestRate = BigDecimal.valueOf(0.2);

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "primary_owner_id")
    private AccountHolder primaryOwner;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "secondary_owner_id")
    private AccountHolder secondaryOwner;
}
