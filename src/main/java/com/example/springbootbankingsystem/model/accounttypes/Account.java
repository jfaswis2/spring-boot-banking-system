package com.example.springbootbankingsystem.model.accounttypes;

import com.example.springbootbankingsystem.model.usertypes.AccountHolder;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private BigDecimal balance;
    private BigDecimal penaltyFee;

    private LocalDate createdDate;
    private LocalDate updateDate;
    private boolean deleted;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "primary_owner_id")
    private AccountHolder primaryOwner;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "secondary_owner_id")
    private AccountHolder secondaryOwner;
}
