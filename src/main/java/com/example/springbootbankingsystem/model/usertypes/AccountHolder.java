package com.example.springbootbankingsystem.model.usertypes;

import com.example.springbootbankingsystem.model.accounttypes.*;
import com.example.springbootbankingsystem.utils.Role;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
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
public class AccountHolder{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    private LocalDate createdDate;
    private LocalDate updateDate;
    private boolean deleted;
    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    private Role role;

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
