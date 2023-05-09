package com.example.springbootbankingsystem.mapper.usermapper;

import com.example.springbootbankingsystem.dto.userdto.PrimaryAddressDTO;
import com.example.springbootbankingsystem.mapper.IMapper;
import com.example.springbootbankingsystem.model.usertypes.AccountHolder;
import com.example.springbootbankingsystem.model.usertypes.PrimaryAddress;
import com.example.springbootbankingsystem.repository.userrepository.AccountHolderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PrimaryAddressDTOMapper implements IMapper<PrimaryAddressDTO, PrimaryAddress> {

    private final AccountHolderRepository accountHolderRepository;
    @Override
    public PrimaryAddress map(PrimaryAddressDTO in) {
        PrimaryAddress primaryAddress = new PrimaryAddress();
        AccountHolder accountHolderOptional = accountHolderRepository.findById(in.idAccountHolder())
                .orElseThrow(() -> new IllegalStateException("La cuenta con el id " + in.idAccountHolder() + " no se encuentra"));

        primaryAddress.setCity(in.city());
        primaryAddress.setCountry(in.country());
        primaryAddress.setStreet(in.street());
        primaryAddress.setApartmentNumber(in.apartmentNumber());
        primaryAddress.setProvince(in.province());
        primaryAddress.setPostalCode(in.postalCode());
        primaryAddress.setAccountHolderPrimary(accountHolderOptional);

        return primaryAddress;
    }
}
