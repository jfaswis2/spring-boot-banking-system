package com.example.springbootbankingsystem.model.usertypes;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "t_admin_users")
@RequiredArgsConstructor
public class Admin extends User{
}
