package com.example.springbootbankingsystem.mapper.accountmapper;

import com.example.springbootbankingsystem.dto.accountdto.CheckingDTO;
import com.example.springbootbankingsystem.dto.accountdto.StudentCheckingDTO;
import com.example.springbootbankingsystem.mapper.IMapper;
import com.example.springbootbankingsystem.model.accounttypes.Checking;
import com.example.springbootbankingsystem.model.accounttypes.StudentChecking;
import com.example.springbootbankingsystem.utils.Status;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class StudentCheckingDTOMapper implements IMapper<StudentCheckingDTO, StudentChecking> {
    @Override
    public StudentChecking map(StudentCheckingDTO in) {
        StudentChecking studentChecking = new StudentChecking();
        studentChecking.setSecretKey(in.secretKey());
        studentChecking.setBalance(in.balance());
        studentChecking.setPenaltyFee(BigDecimal.valueOf(40L));
        studentChecking.setStatus(Status.ACTIVE);
        studentChecking.setCreatedDate(LocalDate.now());
        studentChecking.setUpdateDate(LocalDate.now());
        studentChecking.setDeleted(false);

        return studentChecking;
    }
}
