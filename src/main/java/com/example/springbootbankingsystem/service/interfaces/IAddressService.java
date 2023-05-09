package com.example.springbootbankingsystem.service.interfaces;

import org.springframework.http.ResponseEntity;

public interface IAddressService {

    ResponseEntity<?> addNewAddress(Object address);
}
