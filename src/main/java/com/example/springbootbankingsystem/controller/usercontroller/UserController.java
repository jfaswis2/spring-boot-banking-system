package com.example.springbootbankingsystem.controller.usercontroller;

import com.example.springbootbankingsystem.dto.userdto.AccountHolderDTO;
import com.example.springbootbankingsystem.dto.userdto.AdminDTO;
import com.example.springbootbankingsystem.model.usertypes.AccountHolder;
import com.example.springbootbankingsystem.model.usertypes.Admin;
import com.example.springbootbankingsystem.service.impl.userimpl.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
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
    @Operation(summary = "Register new AccountHolder")
    public ResponseEntity<AccountHolder> registerNewAccountHolder(@RequestBody AccountHolderDTO accountHolderDTO){
        return userService.addNewAccountHolder(accountHolderDTO);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get AccountHolder")
    public ResponseEntity<AccountHolder> getAccountHolder(@PathVariable Long id) {
        return userService.getAccountHolder(id);
    }

    @GetMapping
    @Operation(summary = "Get all AccountHolder")
    public ResponseEntity<List<AccountHolder>> getAllAccountHolder() {
        return userService.getAllAccountHolder();
    }

    @PutMapping("/update/account-holder/{id}")
    @Operation(summary = "Update AccountHolder")
    public ResponseEntity<AccountHolder> updateAccountHolder(@PathVariable Long id,
                                                             @RequestBody AccountHolderDTO accountHolderDTO) {
        return userService.updateAccountHolder(id, accountHolderDTO);
    }

    @DeleteMapping("/delete/account-holder/{id}")
    @Operation(summary = "Delete AccountHolder")
    public ResponseEntity<Void> deleteAccountHolder(@PathVariable Long id) {
        return userService.deleteAccountHolder(id);
    }

    //---------------------- ADMIN -----------------------------
    @PostMapping("/register/admin")
    @Operation(summary = "Register new Admin")
    public ResponseEntity<Admin> registerNewAdmin(@RequestBody AdminDTO adminDTO) {
        return userService.addNewAdmin(adminDTO);
    }

    //--------------------- THIRD-PARTY -----------------------------


}
