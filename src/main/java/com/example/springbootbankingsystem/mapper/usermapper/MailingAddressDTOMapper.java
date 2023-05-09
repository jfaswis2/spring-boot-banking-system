package com.example.springbootbankingsystem.mapper.usermapper;

import com.example.springbootbankingsystem.dto.userdto.MailingAddressDTO;
import com.example.springbootbankingsystem.mapper.IMapper;
import com.example.springbootbankingsystem.model.usertypes.AccountHolder;
import com.example.springbootbankingsystem.model.usertypes.MailingAddress;
import com.example.springbootbankingsystem.repository.userrepository.AccountHolderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MailingAddressDTOMapper implements IMapper<MailingAddressDTO, MailingAddress> {

    private final AccountHolderRepository accountHolderRepository;

    @Override
    public MailingAddress map(MailingAddressDTO in) {
        MailingAddress mailingAddress = new MailingAddress();
        AccountHolder accountHolderOptional = accountHolderRepository.findById(in.idAccountHolder())
                .orElseThrow(() -> new IllegalStateException("La cuenta con el id " + in.idAccountHolder() + " no se encuentra"));

        mailingAddress.setCity(in.city());
        mailingAddress.setCountry(in.country());
        mailingAddress.setStreet(in.street());
        mailingAddress.setApartmentNumber(in.apartmentNumber());
        mailingAddress.setProvince(in.province());
        mailingAddress.setPostalCode(in.postalCode());
        mailingAddress.setAccountHolderMailing(accountHolderOptional);

        return mailingAddress;
    }
}
