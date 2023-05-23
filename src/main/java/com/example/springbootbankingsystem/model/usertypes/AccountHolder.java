package com.example.springbootbankingsystem.model.usertypes;

import com.example.springbootbankingsystem.model.accounttypes.*;
import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "t_accountholder_users")
@RequiredArgsConstructor
public class AccountHolder extends User{
    private LocalDate dateOfBirth;

    @OneToMany(mappedBy = "primaryOwner")
    private List<Checking> primaryOwnerCheckingList;

    @OneToMany(mappedBy = "secondaryOwner")
    private List<Checking> secondaryOwnerCheckingList;

    @OneToMany(mappedBy = "primaryOwner")
    private List<CreditCard> primaryOwnerCreditCardList;

    @OneToMany(mappedBy = "secondaryOwner")
    private List<CreditCard> secondaryOwnerCreditCardList;

    @OneToMany(mappedBy = "primaryOwner")
    private List<Savings> primaryOwnerSavingsList;

    @OneToMany(mappedBy = "secondaryOwner")
    private List<Savings> secondaryOwnerSavingsList;

    @OneToMany(mappedBy = "primaryOwner")
    private List<StudentChecking> primaryOwnerStudentCheckingList;

    @OneToMany(mappedBy = "secondaryOwner")
    private List<StudentChecking> secondaryOwnerStudentCheckingList;

    @OneToMany(mappedBy = "accountHolderPrimary")
    private List<PrimaryAddress> primaryAddressList;

    @OneToMany(mappedBy = "accountHolderMailing")
    private List<MailingAddress> mailingAddressList;
}
