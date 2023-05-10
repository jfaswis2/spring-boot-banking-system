package com.example.springbootbankingsystem.model.accounttypes;

import com.example.springbootbankingsystem.utils.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "t_checking_account")
public class Checking extends Account {
    private String secretKey;
    private BigDecimal balance;
    private BigDecimal minimumBalance;
    private BigDecimal monthlyMaintenanceFee;
    private LocalDate creationDate;
    private Status status;
}
