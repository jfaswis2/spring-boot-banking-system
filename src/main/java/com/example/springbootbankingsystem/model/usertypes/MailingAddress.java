package com.example.springbootbankingsystem.model.usertypes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "t_mailing_address_users")
@RequiredArgsConstructor
public class MailingAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String street;
    private String city;
    private String province;
    private String country;
    private String postalCode;
    private String apartmentNumber;

    @ManyToOne
    @JsonIgnore
    private AccountHolder accountHolderMailing;
}
