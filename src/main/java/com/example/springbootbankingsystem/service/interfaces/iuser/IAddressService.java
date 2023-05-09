package com.example.springbootbankingsystem.service.interfaces.iuser;

import org.springframework.http.ResponseEntity;

public interface IAddressService {

    ResponseEntity<?> addNewAddress(Object address);
}
