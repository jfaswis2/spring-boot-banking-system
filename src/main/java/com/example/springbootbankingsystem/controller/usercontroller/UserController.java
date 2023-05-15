package com.example.springbootbankingsystem.controller.usercontroller;

import com.example.springbootbankingsystem.dto.userdto.AccountHolderDTO;
import com.example.springbootbankingsystem.dto.userdto.AdminDTO;
import com.example.springbootbankingsystem.model.usertypes.AccountHolder;
import com.example.springbootbankingsystem.model.usertypes.Admin;
import com.example.springbootbankingsystem.service.impl.userimpl.UserServiceImpl;
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

    @PostMapping("/register/admin")
    public ResponseEntity<Admin> registerNewAdmin(@RequestBody AdminDTO adminDTO) {
        return userService.addNewAdmin(adminDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountHolder> getAccountHolder(@PathVariable Long id) {
        return userService.getAccountHolder(id);
    }
}
