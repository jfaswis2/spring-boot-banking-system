package com.example.springbootbankingsystem.controller.usercontroller;

import com.example.springbootbankingsystem.dto.userdto.AccountHolderDTO;
import com.example.springbootbankingsystem.dto.userdto.AdminDTO;
import com.example.springbootbankingsystem.model.usertypes.AccountHolder;
import com.example.springbootbankingsystem.model.usertypes.Admin;
import com.example.springbootbankingsystem.service.impl.userimpl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserServiceImpl userService;

    //----------------------- ACCOUNT-HOLDER ------------------------
    @PostMapping("/register/account-holder")
    public ResponseEntity<AccountHolder> registerNewAccountHolder(@RequestBody AccountHolderDTO accountHolderDTO){
        return userService.addNewAccountHolder(accountHolderDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountHolder> getAccountHolder(@PathVariable Long id) {
        return userService.getAccountHolder(id);
    }

    @GetMapping
    public ResponseEntity<List<AccountHolder>> getAllAccountHolder() {
        return userService.getAllAccountHolder();
    }

    @PutMapping("/update/account-holder/{id}")
    public ResponseEntity<AccountHolder> updateAccountHolder(@PathVariable Long id,
                                                             @RequestBody AccountHolderDTO accountHolderDTO) {
        return userService.updateAccountHolder(id, accountHolderDTO);
    }

    //---------------------- ADMIN -----------------------------
    @PostMapping("/register/admin")
    public ResponseEntity<Admin> registerNewAdmin(@RequestBody AdminDTO adminDTO) {
        return userService.addNewAdmin(adminDTO);
    }

    //--------------------- THIRD-PARTY -----------------------------


}
