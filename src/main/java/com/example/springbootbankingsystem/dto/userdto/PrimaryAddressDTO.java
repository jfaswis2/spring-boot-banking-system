package com.example.springbootbankingsystem.dto.userdto;

public record PrimaryAddressDTO(
        Long idAccountHolder,
        String street,
        String city,
        String province,
        String country,
        String postalCode,
        String apartmentNumber
) {
}
