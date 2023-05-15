package com.example.springbootbankingsystem.mapper.usermapper;

import com.example.springbootbankingsystem.dto.userdto.AdminDTO;
import com.example.springbootbankingsystem.mapper.IMapper;
import com.example.springbootbankingsystem.model.usertypes.Admin;
import com.example.springbootbankingsystem.utils.Role;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class AdminDTOMapper implements IMapper<AdminDTO, Admin> {
    @Override
    public Admin map(AdminDTO in) {
        Admin admin = new Admin();
        admin.setName(in.name());
        admin.setEmail(in.email());
        admin.setPassword(in.password());
        admin.setRole(Role.ADMIN);
        admin.setDeleted(false);
        admin.setCreatedDate(LocalDate.now());
        admin.setUpdateDate(LocalDate.now());

        return admin;
    }
}
