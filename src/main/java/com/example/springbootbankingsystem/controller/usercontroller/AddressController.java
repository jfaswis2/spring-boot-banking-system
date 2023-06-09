package com.example.springbootbankingsystem.controller.usercontroller;

import com.example.springbootbankingsystem.dto.userdto.MailingAddressDTO;
import com.example.springbootbankingsystem.dto.userdto.PrimaryAddressDTO;
import com.example.springbootbankingsystem.service.impl.userimpl.AddressServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/addresses")
public class AddressController {
    private final AddressServiceImpl addressService;

    @PostMapping("/register/primary-address")
    @Operation(summary = "Register new Primary Address")
    public ResponseEntity<?> registerNewAddress(@RequestBody PrimaryAddressDTO primaryAddressDTO) {
        return addressService.addNewAddress(primaryAddressDTO);
    }

    @PostMapping("/register/mailing-address")
    @Operation(summary = "Register new Mailing Address")
    public ResponseEntity<?> registerNewAddress(@RequestBody MailingAddressDTO mailingAddressDTO) {
        return addressService.addNewAddress(mailingAddressDTO);
    }

}
