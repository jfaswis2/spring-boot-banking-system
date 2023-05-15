package com.example.springbootbankingsystem.service.impl.userimpl;

import com.example.springbootbankingsystem.dto.userdto.AccountHolderDTO;
import com.example.springbootbankingsystem.dto.userdto.AdminDTO;
import com.example.springbootbankingsystem.dto.userdto.ThirdPartyDTO;
import com.example.springbootbankingsystem.mapper.usermapper.AccountHolderDTOMapper;
import com.example.springbootbankingsystem.mapper.usermapper.AdminDTOMapper;
import com.example.springbootbankingsystem.model.usertypes.AccountHolder;
import com.example.springbootbankingsystem.model.usertypes.Admin;
import com.example.springbootbankingsystem.model.usertypes.ThirdParty;
import com.example.springbootbankingsystem.repository.userrepository.AccountHolderRepository;
import com.example.springbootbankingsystem.repository.userrepository.AdminRepository;
import com.example.springbootbankingsystem.service.interfaces.iuser.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final AccountHolderRepository accountHolderRepository;

    private final AccountHolderDTOMapper accountHolderDTOMapper;

    private final AdminRepository adminRepository;
    private final AdminDTOMapper adminDTOMapper;


    //------------------------- ACCOUNT-HOLDER -----------------------
    @Override
    public ResponseEntity<AccountHolder> addNewAccountHolder(AccountHolderDTO accountHolderDTO) {
        Optional<AccountHolder> accountHolderOptional = accountHolderRepository
                .findAccountHolderByEmail(accountHolderDTO.email());
        if (accountHolderOptional.isPresent()) {
            throw new IllegalStateException("Ya hay un email vinculado a esta cuenta ACCOUNT-HOLDER");
        }

        return new ResponseEntity<>(accountHolderRepository.save(accountHolderDTOMapper
                .map(accountHolderDTO)), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<AccountHolder> getAccountHolder(Long id) {
        Optional<AccountHolder> accountHolderOptional = accountHolderRepository
                .findById(id);
        if (accountHolderOptional.isEmpty()) {
            throw new IllegalStateException("No existe una cuenta vinculada a este ID");
        }

        return new ResponseEntity<>(accountHolderOptional.get(), HttpStatus.OK);
    }

    //TODO cambiar a que devuelva solo los registros donde Deleted = false
    @Override
    public ResponseEntity<List<AccountHolder>> getAllAccountHolder() {
        return new ResponseEntity<>(accountHolderRepository.findAll(), HttpStatus.OK);

    }


    //TODO añadir todas las comprobaciones
    @Override
    public ResponseEntity<AccountHolder> updateAccountHolder(AccountHolder accountHolder) {

        return new ResponseEntity<>(accountHolderRepository.save(accountHolder), HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<Void> deleteAccountHolder(Long id) {
        AccountHolder accountHolder = accountHolderRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("No se ha encontrado una cuenta con ese ID"));

        accountHolder.setDeleted(true);
        accountHolderRepository.save(accountHolder);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //----------------------- ADMIN --------------------------------
    @Override
    public ResponseEntity<Admin> addNewAdmin(AdminDTO adminDTO) {
        Optional<Admin> adminOptional = adminRepository.findAccountHolderByEmail(adminDTO.email());
        if (adminOptional.isPresent()) {
            throw new IllegalStateException("Ya hay un email vinculado a esta cuenta ADMIN");
        }

        return new ResponseEntity<>(adminRepository.save(adminDTOMapper.map(adminDTO)), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Admin> getAdmin(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<List<Admin>> getAllAdmin() {
        return null;
    }

    @Override
    public ResponseEntity<Admin> updateAdmin(Admin admin) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteAdmin(Long id) {
        return null;
    }

    //----------------------- THIRD-PARTY -----------------------------
    @Override
    public ResponseEntity<ThirdParty> addNewThirdParty(ThirdPartyDTO thirdPartyDTO) {
        return null;
    }

    @Override
    public ResponseEntity<ThirdParty> getThirdParty(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<List<ThirdParty>> getAllThirdParty() {
        return null;
    }

    @Override
    public ResponseEntity<ThirdParty> updateThirdParty(ThirdParty thirdParty) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteThirdParty(Long id) {
        return null;
    }
}
