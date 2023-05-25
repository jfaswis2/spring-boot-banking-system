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
@Table(name = "t_admin_users")
@RequiredArgsConstructor
public class Admin{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    private LocalDate createdDate;
    private LocalDate updateDate;
    private boolean deleted;

    @Enumerated(EnumType.STRING)
    private Role role;
}
