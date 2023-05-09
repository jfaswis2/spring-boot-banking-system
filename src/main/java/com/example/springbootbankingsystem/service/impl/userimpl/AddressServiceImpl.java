package com.example.springbootbankingsystem.service.impl.userimpl;

import com.example.springbootbankingsystem.dto.userdto.MailingAddressDTO;
import com.example.springbootbankingsystem.dto.userdto.PrimaryAddressDTO;
import com.example.springbootbankingsystem.mapper.usermapper.MailingAddressDTOMapper;
import com.example.springbootbankingsystem.mapper.usermapper.PrimaryAddressDTOMapper;
import com.example.springbootbankingsystem.repository.userrepository.MailingAddressRepository;
import com.example.springbootbankingsystem.repository.userrepository.PrimaryAddressRepository;
import com.example.springbootbankingsystem.service.interfaces.iuser.IAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements IAddressService {

    private final PrimaryAddressRepository primaryAddressRepository;
    private final MailingAddressRepository mailingAddressRepository;
    private final PrimaryAddressDTOMapper primaryAddressDTOMapper;
    private final MailingAddressDTOMapper mailingAddressDTOMapper;

    @Override
    public ResponseEntity<?> addNewAddress(Object address) {
        if(address instanceof PrimaryAddressDTO) {
               return new ResponseEntity<>(primaryAddressRepository
                       .save(primaryAddressDTOMapper.map((PrimaryAddressDTO) address)), HttpStatus.CREATED);
        } else if (address instanceof MailingAddressDTO) {
            return new ResponseEntity<>(mailingAddressRepository
                    .save(mailingAddressDTOMapper.map((MailingAddressDTO) address)), HttpStatus.CREATED);
        } else {
            throw new IllegalStateException("No se ha podido agregar la dirección");
        }
    }
}
