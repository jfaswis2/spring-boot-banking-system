package com.example.springbootbankingsystem.model.usertypes;

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

    @OneToMany(mappedBy = "accountHolderPrimary")
    private List<PrimaryAddress> primaryAddressList = new ArrayList<>();

    @OneToMany(mappedBy = "accountHolderMailing")
    private List<MailingAddress> mailingAddressList = new ArrayList<>();
}
