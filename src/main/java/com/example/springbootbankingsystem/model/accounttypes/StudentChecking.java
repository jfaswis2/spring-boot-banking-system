package com.example.springbootbankingsystem.model.accounttypes;

import com.example.springbootbankingsystem.utils.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "t_studentchecking_account")
public class StudentChecking extends Checking{
    private String secretKey;
    private LocalDate creationDate;
    private Status status;
}
