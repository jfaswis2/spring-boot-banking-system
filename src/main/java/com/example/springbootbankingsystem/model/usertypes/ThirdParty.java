package com.example.springbootbankingsystem.model.usertypes;

import com.example.springbootbankingsystem.utils.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "t_thirdparty_users")
@RequiredArgsConstructor
public class ThirdParty{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String password;
    private String hashedKey;

    @Enumerated(EnumType.STRING)
    private Role role;
    private LocalDate createdDate;
    private LocalDate updateDate;
    private boolean deleted;
}
