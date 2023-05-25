package com.example.springbootbankingsystem.model.usertypes;

import com.example.springbootbankingsystem.utils.Role;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jdk.jfr.BooleanFlag;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)

//TODO ELIMINAR CLASE
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;

    private LocalDate createdDate;
    private LocalDate updateDate;
    private boolean deleted;
}
