package com.example.springbootbankingsystem.dto;

import java.time.LocalDate;
public record AccountHolderDTO(
        String name,
        String email,
        String password,
        LocalDate dateOfBirth
) {

}
