package com.example.springbootbankingsystem.model.usertypes;

import com.example.springbootbankingsystem.model.accounttypes.Account;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "t_accountholder_users")
@RequiredArgsConstructor
public class AccountHolder extends User{
    private LocalDate dateOfBirth;

    @OneToMany(mappedBy = "primaryOwner")
    private List<Account> primaryOwnerList;

    @OneToMany(mappedBy = "secondaryOwner")
    private List<Account> secondaryOwnerList;

    @OneToMany(mappedBy = "accountHolderPrimary")
    private List<PrimaryAddress> primaryAddressList;

    @OneToMany(mappedBy = "accountHolderMailing")
    private List<MailingAddress> mailingAddressList;
}
