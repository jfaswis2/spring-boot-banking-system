package com.example.springbootbankingsystem.controller;

import com.example.springbootbankingsystem.dto.AccountHolderDTO;
import com.example.springbootbankingsystem.model.usertypes.AccountHolder;
import com.example.springbootbankingsystem.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserServiceImpl userService;

    @PostMapping("/register/account-holder")
    public ResponseEntity<AccountHolder> registerNewAccountHolder(@RequestBody AccountHolderDTO accountHolderDTO){
        return userService.addNewAccountHolder(accountHolderDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountHolder> getAccountHolder(@PathVariable Long id) {
        return userService.getAccountHolder(id);
    }
}
