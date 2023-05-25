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

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
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

        return new ResponseEntity<>(accountHolderRepository.save(accountHolderDTOMapper.map(accountHolderDTO)),
                HttpStatus.CREATED);
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

    @Override
    public ResponseEntity<List<AccountHolder>> getAllAccountHolder() {
        return new ResponseEntity<>(accountHolderRepository.findAllAccountHolder(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AccountHolder> updateAccountHolder(Long id, AccountHolderDTO accountHolderDTO) {
        AccountHolder accountHolder1 = accountHolderRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("No se ha encontrado el account holder para actualizar"));

        if (accountHolderDTO.name() != null &&
                accountHolderDTO.name().length() > 0 &&
                !Objects.equals(accountHolder1.getName(), accountHolderDTO.name())){
            accountHolder1.setName(accountHolderDTO.name());
        }

        if (accountHolderDTO.password() != null &&
                accountHolderDTO.password().length() > 0 &&
                !Objects.equals(accountHolder1.getPassword(), accountHolderDTO.password())){
            accountHolder1.setPassword(accountHolderDTO.password());
        }

        if (accountHolderDTO.dateOfBirth() != null &&
                !Objects.equals(accountHolder1.getDateOfBirth(), accountHolderDTO.dateOfBirth())){
            accountHolder1.setDateOfBirth(accountHolderDTO.dateOfBirth());
        }

        if (accountHolderDTO.email() != null &&
                accountHolderDTO.email().length() > 0 &&
                !Objects.equals(accountHolder1.getEmail(), accountHolderDTO.email())){

            Optional<AccountHolder> accountHolderOptional = accountHolderRepository
                    .findAccountHolderByEmail(accountHolderDTO.email());

            if (accountHolderOptional.isPresent()) {
                throw new IllegalStateException("El nuevo email ya ha sido vinculado a otra cuenta");
            }
            accountHolder1.setEmail(accountHolderDTO.email());
        }

        accountHolder1.setUpdateDate(LocalDate.now());

        return new ResponseEntity<>(accountHolderRepository.save(accountHolder1), HttpStatus.ACCEPTED);
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
