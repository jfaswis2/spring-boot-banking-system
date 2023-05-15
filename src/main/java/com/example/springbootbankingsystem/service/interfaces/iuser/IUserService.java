package com.example.springbootbankingsystem.service.interfaces.iuser;

import com.example.springbootbankingsystem.dto.userdto.AccountHolderDTO;
import com.example.springbootbankingsystem.dto.userdto.AdminDTO;
import com.example.springbootbankingsystem.dto.userdto.ThirdPartyDTO;
import com.example.springbootbankingsystem.model.usertypes.AccountHolder;
import com.example.springbootbankingsystem.model.usertypes.Admin;
import com.example.springbootbankingsystem.model.usertypes.ThirdParty;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IUserService {

    //-------------------------- ACCOUNT-HOLDER -----------------------------
    ResponseEntity<AccountHolder> addNewAccountHolder(AccountHolderDTO accountHolderDTO);

    ResponseEntity<AccountHolder> getAccountHolder(Long id);

    ResponseEntity<List<AccountHolder>> getAllAccountHolder();

    ResponseEntity<AccountHolder> updateAccountHolder(AccountHolder accountHolder);

    ResponseEntity<Void> deleteAccountHolder(Long id);

    //----------------------------- ADMIN -----------------------------------
    ResponseEntity<Admin> addNewAdmin(AdminDTO adminDTO);

    ResponseEntity<Admin> getAdmin(Long id);

    ResponseEntity<List<Admin>> getAllAdmin();

    ResponseEntity<Admin> updateAdmin(Admin admin);

    ResponseEntity<Void> deleteAdmin(Long id);

    //----------------------- THIRD-PARTY ------------------------------------

    ResponseEntity<ThirdParty> addNewThirdParty(ThirdPartyDTO thirdPartyDTO);

    ResponseEntity<ThirdParty> getThirdParty(Long id);

    ResponseEntity<List<ThirdParty>> getAllThirdParty();

    ResponseEntity<ThirdParty> updateThirdParty(ThirdParty thirdParty);

    ResponseEntity<Void> deleteThirdParty(Long id);
}
