package com.example.springbootbankingsystem.mapper.usermapper;

import com.example.springbootbankingsystem.dto.userdto.ThirdPartyDTO;
import com.example.springbootbankingsystem.mapper.IMapper;
import com.example.springbootbankingsystem.model.usertypes.ThirdParty;
import com.example.springbootbankingsystem.utils.Role;

import java.time.LocalDate;

public class ThirdPartyDTOMapper implements IMapper<ThirdPartyDTO, ThirdParty> {
    @Override
    public ThirdParty map(ThirdPartyDTO in) {
        ThirdParty thirdParty = new ThirdParty();
        thirdParty.setName(in.name());
        thirdParty.setPassword(in.password());
        thirdParty.setRole(Role.THIRD_PARTY);
        thirdParty.setDeleted(false);
        thirdParty.setUpdateDate(LocalDate.now());
        thirdParty.setCreatedDate(LocalDate.now());
        thirdParty.setHashedKey(null); //TODO crear hashed key

        return thirdParty;
    }
}
