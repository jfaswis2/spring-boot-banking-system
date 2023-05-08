package com.example.springbootbankingsystem.model.accounttypes;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
    private BigDecimal creditLimit;
    private BigDecimal interestRate;
}
