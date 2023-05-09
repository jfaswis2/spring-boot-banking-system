package com.example.springbootbankingsystem.service.impl;

import com.example.springbootbankingsystem.dto.MailingAddressDTO;
import com.example.springbootbankingsystem.dto.PrimaryAddressDTO;
import com.example.springbootbankingsystem.mapper.MailingAddressDTOMapper;
import com.example.springbootbankingsystem.mapper.PrimaryAddressDTOMapper;
import com.example.springbootbankingsystem.repository.MailingAddressRepository;
import com.example.springbootbankingsystem.repository.PrimaryAddressRepository;
import com.example.springbootbankingsystem.service.interfaces.IAddressService;
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
