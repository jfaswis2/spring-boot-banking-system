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

    @GetMapping("/account-holder/{id}")
    @Operation(summary = "Get AccountHolder")
    public ResponseEntity<AccountHolder> getAccountHolder(@PathVariable Long id) {
        return userService.getAccountHolder(id);
    }

    @GetMapping("/all/account-holder")
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

    @GetMapping("/admin/{id}")
    @Operation(summary = "Get Admin")
    public ResponseEntity<Admin> getAdmin(@PathVariable Long id) {
        return userService.getAdmin(id);
    }

    @GetMapping("/all/admin")
    @Operation(summary = "Get all Admin")
    public ResponseEntity<List<Admin>> getAllAdmin() {
        return userService.getAllAdmin();
    }

    @PutMapping("/update/admin/{id}")
    @Operation(summary = "Update Admin")
    public ResponseEntity<Admin> updateAdmin(@PathVariable Long id,
                                                             @RequestBody Admin admin) {
        return userService.updateAdmin(id, admin);
    }

    @DeleteMapping("/delete/admin/{id}")
    @Operation(summary = "Delete Admin")
    public ResponseEntity<Void> deleteAdmin(@PathVariable Long id) {
        return userService.deleteAdmin(id);
    }

    //--------------------- THIRD-PARTY -----------------------------


}
